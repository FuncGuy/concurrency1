import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class EnhancedStream<T> implements Stream<T> {

    private static final class Key<E> {
        private final E e;
        private final ToIntFunction<E> hashCode;
        private final BiPredicate<E, E> equals;

        public Key(E e, ToIntFunction<E> hashCode,
                   BiPredicate<E, E> equals) {
            this.e = e;
            this.hashCode = hashCode;
            this.equals = equals;
        }

        public int hashCode() {
            return hashCode.applyAsInt(e);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Key)) return false;
            @SuppressWarnings("unchecked")
            Key<E> that = (Key<E>) obj;
            return equals.test(this.e, that.e);
        }
    }

    private Stream<T> delegate;

    public EnhancedStream(Stream<T> delegate) {
        this.delegate = delegate;
    }

    public EnhancedStream<T> distinct(ToIntFunction<T> hashCode,
                                      BiPredicate<T, T> equals,
                                      BinaryOperator<T> merger) {
        delegate = collect(Collectors.toMap(
                t -> new Key<>(t, hashCode, equals),
                t -> t,
                merger,
                LinkedHashMap::new))
                // thanks Federico Peralta Schaffner for suggesting that
                // we use a LinkedHashMap.  That way we can preserve the
                // original order and get the speed of hashing.
                .values()
                .stream();
        return this;
    }

    public EnhancedStream<T> filter(
            Predicate<? super T> predicate) {
        this.delegate = delegate.filter(predicate);
        return this;
    }

    public <R> EnhancedStream<R> map(
            Function<? super T, ? extends R> mapper) {
        return new EnhancedStream<>(delegate.map(mapper));
    }

    public IntStream mapToInt(ToIntFunction<? super T> mapper) {
        return delegate.mapToInt(mapper);
    }

    public LongStream mapToLong(
            ToLongFunction<? super T> mapper) {
        return delegate.mapToLong(mapper);
    }

    public DoubleStream mapToDouble(
            ToDoubleFunction<? super T> mapper) {
        return delegate.mapToDouble(mapper);
    }

    public <R> EnhancedStream<R> flatMap(
            Function<? super T,
                    ? extends Stream<? extends R>> mapper) {
        return new EnhancedStream<>(delegate.flatMap(mapper));
    }

    public IntStream flatMapToInt(
            Function<? super T, ? extends IntStream> mapper) {
        return delegate.flatMapToInt(mapper);
    }

    public LongStream flatMapToLong(
            Function<? super T, ? extends LongStream> mapper) {
        return delegate.flatMapToLong(mapper);
    }

    public DoubleStream flatMapToDouble(
            Function<? super T, ? extends DoubleStream> mapper) {
        return delegate.flatMapToDouble(mapper);
    }

    public EnhancedStream<T> distinct() {
        delegate = delegate.distinct();
        return this;
    }

    public EnhancedStream<T> sorted() {
        delegate = delegate.sorted();
        return this;
    }

    public EnhancedStream<T> sorted(
            Comparator<? super T> comparator) {
        delegate = delegate.sorted(comparator);
        return this;
    }

    public EnhancedStream<T> peek(Consumer<? super T> action) {
        delegate = delegate.peek(action);
        return this;
    }

    public EnhancedStream<T> limit(long maxSize) {
        delegate = delegate.limit(maxSize);
        return this;
    }

    public EnhancedStream<T> skip(long n) {
        delegate = delegate.skip(n);
        return this;
    }

    public EnhancedStream<T> takeWhile(
            Predicate<? super T> predicate) {
        delegate = delegate.takeWhile(predicate);
        return this;
    }

    public EnhancedStream<T> dropWhile(
            Predicate<? super T> predicate) {
        delegate = delegate.dropWhile(predicate);
        return this;
    }

    public void forEach(Consumer<? super T> action) {
        delegate.forEach(action);
    }

    public void forEachOrdered(Consumer<? super T> action) {
        delegate.forEachOrdered(action);
    }

    public Object[] toArray() {
        return delegate.toArray();
    }

    public <A> A[] toArray(IntFunction<A[]> generator) {
        return delegate.toArray(generator);
    }

    public T reduce(T identity, BinaryOperator<T> accumulator) {
        return delegate.reduce(identity, accumulator);
    }

    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        return delegate.reduce(accumulator);
    }

    public <U> U reduce(U identity,
                        BiFunction<U, ? super T, U> accumulator,
                        BinaryOperator<U> combiner) {
        return delegate.reduce(identity, accumulator, combiner);
    }

    public <R> R collect(Supplier<R> supplier,
                         BiConsumer<R, ? super T> accumulator,
                         BiConsumer<R, R> combiner) {
        return delegate.collect(supplier, accumulator, combiner);
    }

    public <R, A> R collect(
            Collector<? super T, A, R> collector) {
        return delegate.collect(collector);
    }

    public Optional<T> min(Comparator<? super T> comparator) {
        return delegate.min(comparator);
    }

    public Optional<T> max(Comparator<? super T> comparator) {
        return delegate.max(comparator);
    }

    public long count() {
        return delegate.count();
    }

    public boolean anyMatch(Predicate<? super T> predicate) {
        return delegate.anyMatch(predicate);
    }

    public boolean allMatch(Predicate<? super T> predicate) {
        return delegate.allMatch(predicate);
    }

    public boolean noneMatch(Predicate<? super T> predicate) {
        return delegate.noneMatch(predicate);
    }

    public Optional<T> findFirst() {
        return delegate.findFirst();
    }

    public Optional<T> findAny() {
        return delegate.findAny();
    }

    public Iterator<T> iterator() {
        return delegate.iterator();
    }

    public Spliterator<T> spliterator() {
        return delegate.spliterator();
    }

    public boolean isParallel() {
        return delegate.isParallel();
    }

    public Stream<T> sequential() {
        return delegate.sequential();
    }

    public Stream<T> parallel() {
        return delegate.parallel();
    }

    public Stream<T> unordered() {
        return delegate.unordered();
    }

    public Stream<T> onClose(Runnable closeHandler) {
        return delegate.onClose(closeHandler);
    }

    public void close() {
        delegate.close();
    }

    public static <T> EnhancedStream<T> of(T t) {
        return new EnhancedStream<>(Stream.of(t));
    }

    @SafeVarargs
    @SuppressWarnings("varargs")
    // Creating a stream from an array is safe
    public static <T> EnhancedStream<T> of(T... values) {
        return new EnhancedStream(Arrays.stream(values));
    }

    public static void main(String[] args) {
        ToIntFunction<String> HASH_CODE =
                s -> s.toUpperCase().hashCode();
        BiPredicate<String, String> EQUALS =
                (s1, s2) ->
                        s1.toUpperCase().equals(s2.toUpperCase());
        BinaryOperator<String> MERGE =
                (s1, s2) ->
                        s1.chars().sum() < s2.chars().sum() ? s2 : s1;

        EnhancedStream.of("Kalathas", "Stavros", "STAVROS",
                "marathi", "kalathas", "baLos", "Balos", "BALOS")

                .distinct(HASH_CODE, EQUALS, MERGE)

                .forEach(System.out::println);
    }
}
  