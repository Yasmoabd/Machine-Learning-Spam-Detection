public class TestingInstance {
    private boolean type;
    private String email;

    public TestingInstance(boolean t, String e){
        type = t;
        email = e;
    }

    public boolean getType(){
        return type;
    }

    public String getEmail(){
        return email;
    }
}
