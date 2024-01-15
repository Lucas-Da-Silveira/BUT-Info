public class LineNumberDecorator implements TextBuffer {
    private TextBuffer textBuffer;

    public LineNumberDecorator(TextBuffer textBuffer) {
        this.textBuffer = textBuffer;
    }

    @Override
    public String getText() {
        StringBuilder decoratedText = new StringBuilder();
        String[] lines = textBuffer.getText().split("\n");

        for (int i = 0; i < lines.length; i++) {
            decoratedText.append(i + 1).append(".").append(lines[i]);
            if (i < lines.length - 1) {
                decoratedText.append("\n");
            }
        }
        return decoratedText.toString();
    }
}
