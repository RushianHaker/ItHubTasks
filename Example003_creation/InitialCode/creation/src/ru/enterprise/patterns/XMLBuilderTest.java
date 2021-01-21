package ru.enterprise.patterns;

/*
 * @created 19/01/2021 - 0:11
 * @project IntelliJ IDEA
 * @author Temnyakov Nikolay
 */
public class XMLBuilderTest extends AbsBuiderTest {

    public void TestAddAboveRoot(){
        String invalidResult =
                "<orders>" +
                        "<order>" +
                        "</order>" +
                        "</orders>" +
                        "<customer>" +
                        "</customer>";

        builder = createBuilder("orders");

        builder.AddBelow("order");

        try {
            builder.AddBul("customer");
            Fail("expecting RuntimeException");
        } catch (RuntimeException ignored) {

        }
    }

    private void Fail(String failureMessage) {

    }

    @Override
    public OutputBuilder createBuilder(String root){
        return new XMLBuilder("order");
    }

}
