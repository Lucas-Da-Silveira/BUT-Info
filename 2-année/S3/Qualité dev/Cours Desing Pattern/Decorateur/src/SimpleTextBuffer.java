public class SimpleTextBuffer implements TextBuffer {
    private String text;

    public SimpleTextBuffer(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }
}
