package in.innoskrit.mode;

public class ConsolePrint implements Print {

    @Override
    public void printData(String data) {
        System.out.println(data);
    }
}
