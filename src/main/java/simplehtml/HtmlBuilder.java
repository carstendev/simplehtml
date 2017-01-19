package simplehtml;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 'Fluent Interface' like helper class to create simple HTML in java.
 * 
 * @author c.timpert
 *
 */
public final class HtmlBuilder implements CharSequence {

    private enum AutoClose {
        ON, OFF
    }

    private static final String NON_BREAKING_SPACE = "&nbsp;";

    /**
     * StringBuilder that is used to create the HTML.
     */
    private final StringBuilder builder;

    private AutoClose autoClose;

    /**
     * Deque that is used to save all open tags.
     */
    private final Deque<String> openTagDeque = new ArrayDeque<>();

    private HtmlBuilder(AutoClose autoClose) {
        builder = new StringBuilder();
        this.autoClose = autoClose;
    }

    /**
     * Creates a builder that automatically closes the last open HTML tag after {@link #text(String)} is called. Auto
     * closing builders also close all open HTML tags when {@link #toString()} is called.
     */
    public static HtmlBuilder autoClosing() {
        return new HtmlBuilder(AutoClose.ON);
    }

    /**
     * Creates a builder that does not automatically close HTML tags.
     */
    public static HtmlBuilder nonAutoClosing() {
        return new HtmlBuilder(AutoClose.OFF);
    }

    /**
     * If an autoclosing builder is used, the last open HTML tag is closed.
     * 
     * @param content the content that is to be added to the HTML.
     */
    public HtmlBuilder text(String content) {
        write(content);
        if (autoClose == AutoClose.ON) {
            closeTag();
        }
        return this;
    }

    private void write(String text) {
        if (text != null) {
            builder.append(text);
        }
    }

    /**
     * Closes the last open tag.
     */
    private void closeTag() {
        if (!openTagDeque.isEmpty()) {
            builder.append(openTagDeque.pop());
        }
    }

    /**
     * Only closes the last open tag, if it is equal to the specified tag.
     * 
     * @throws IllegalStateException if the tags are not equal.
     */
    private void closeTag(String expectedTag) {
        String tag = openTagDeque.pop();
        if (!tag.equals(expectedTag)) {
            throw new IllegalStateException("Html not valid! Excpected Tag: " + expectedTag + " actual: " + tag);
        }
        builder.append(tag);
    }

    /**
     * Turns the created HTML into its String representation. If an autoclosing builder is used, all open tags are closed.
     */
    @Override
    public String toString() {
        if (autoClose == AutoClose.ON) {
            while (!openTagDeque.isEmpty()) {
                closeTag();
            }
        }
        return builder.toString();
    }

    /**
     * Opens the given HtmlTag tag.
     * 
     * @param tag the tag to open
     * @return the builder itself.
     */
    public HtmlBuilder open(HtmlTag tag) {
        builder.append(tag.getOpeningTag());
        openTagDeque.push(tag.getClosingTag().orElse(""));
        return this;
    }

    /**
     * Closes the given HTML tag.
     * 
     * @param tag the HTML tag to close.
     * @return the builder itself.
     */
    public HtmlBuilder close(HtmlTag tag) {
        closeTag(tag.getClosingTag().orElse(""));
        return this;
    }

    public HtmlBuilder html() {
        builder.append(Tag.HTML.getOpeningTag());
        openTagDeque.push(Tag.HTML.getClosingTag().orElse(""));
        return this;
    }

    public HtmlBuilder _html() {
        closeTag(Tag.HTML.getClosingTag().orElse(""));
        return this;
    }

    public HtmlBuilder head() {
        builder.append(Tag.HEAD.getOpeningTag());
        openTagDeque.push(Tag.HEAD.getClosingTag().orElse(""));
        return this;
    }

    public HtmlBuilder _head() {
        closeTag(Tag.HEAD.getClosingTag().orElse(""));
        return this;
    }

    public HtmlBuilder body() {
        builder.append(Tag.BODY.getOpeningTag());
        openTagDeque.push(Tag.BODY.getClosingTag().orElse(""));
        return this;
    }

    public HtmlBuilder _body() {
        closeTag(Tag.BODY.getClosingTag().orElse(""));
        return this;
    }

    public HtmlBuilder htmlAndBody() {
        html();
        body();
        return this;
    }

    public HtmlBuilder _bodyAndHtml() {
        _body();
        _html();
        return this;
    }

    public HtmlBuilder h(Tag header) {
        builder.append(header.getOpeningTag());
        openTagDeque.push(header.getClosingTag().orElse(""));
        return this;
    }

    public HtmlBuilder _h(Tag header) {
        closeTag(header.getClosingTag().orElse(""));
        return this;
    }

    public HtmlBuilder p() {
        builder.append(Tag.P.getOpeningTag());
        openTagDeque.push(Tag.P.getClosingTag().orElse(""));
        return this;
    }

    public HtmlBuilder _p() {
        closeTag(Tag.P.getClosingTag().orElse(""));
        return this;
    }

    public HtmlBuilder pre() {
        builder.append(Tag.PRE.getOpeningTag());
        openTagDeque.push(Tag.PRE.getClosingTag().orElse(""));
        return this;
    }

    public HtmlBuilder _pre() {
        closeTag(Tag.PRE.getClosingTag().orElse(""));
        return this;
    }

    public HtmlBuilder _span() {
        closeTag(Tag.SPAN.getClosingTag().orElse(""));
        return this;
    }

    public HtmlBuilder span() {
        builder.append(Tag.SPAN.getOpeningTag());
        openTagDeque.push(Tag.SPAN.getClosingTag().orElse(""));
        return this;
    }

    public HtmlBuilder bold() {
        builder.append(Tag.BOLD.getOpeningTag());
        openTagDeque.push(Tag.BOLD.getClosingTag().orElse(""));
        return this;
    }

    public HtmlBuilder _bold() {
        closeTag(Tag.BOLD.getClosingTag().orElse(""));
        return this;
    }

    public HtmlBuilder italic() {
        builder.append(Tag.ITALIC.getOpeningTag());
        openTagDeque.push(Tag.ITALIC.getClosingTag().orElse(""));
        return this;
    }

    public HtmlBuilder _italic() {
        closeTag(Tag.ITALIC.getClosingTag().orElse(""));
        return this;
    }

    public HtmlBuilder underline() {
        builder.append(Tag.UNDERLINE.getOpeningTag());
        openTagDeque.push(Tag.UNDERLINE.getClosingTag().orElse(""));
        return this;
    }

    public HtmlBuilder _underline() {
        closeTag(Tag.UNDERLINE.getClosingTag().orElse(""));
        return this;
    }

    public HtmlBuilder br() {
        builder.append("<br>");
        return this;
    }

    public HtmlBuilder space(int times) {
        String spaces = FormattingUtil.repeatString(" ", times);
        builder.append(spaces);
        return this;
    }

    public HtmlBuilder space() {
        builder.append(" ");
        return this;
    }

    public HtmlBuilder nonBreakingSpace(int times) {
        String nbsp = FormattingUtil.repeatString(NON_BREAKING_SPACE, times);
        builder.append(nbsp);
        return this;
    }

    public HtmlBuilder nonBreakingSpace() {
        builder.append(NON_BREAKING_SPACE);
        return this;
    }

    public void setAutoClose(AutoClose autoClose) {
        this.autoClose = autoClose;
    }

    public int length() {
        return builder.length();
    }

    public char charAt(int index) {
        return builder.charAt(index);
    }

    public CharSequence subSequence(int start, int end) {
        return builder.subSequence(start, end);
    }
}
