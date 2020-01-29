import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

@NoArgsConstructor
public abstract class AbstractExternalizable implements Externalizable {

    public AbstractExternalizable() {

        //ClassChecker.checkConstructorIsNoArgsPublic(getClass());
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

        throw new UnsupportedOperationException();

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException();
    }
}
