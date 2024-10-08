package jkas.androidpe.codeEditor.schemes;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;

/**
 * @author JKas
 */
public class EditorColorSchemeDark extends EditorColorScheme {
    public EditorColorSchemeDark() {
        super(true);
    }

    @Override
    public void applyDefault() {
        super.applyDefault();
        
        setColor(ANNOTATION, 0xffbbb529);
        setColor(FUNCTION_NAME, 0xffffffff);
        setColor(IDENTIFIER_NAME, 0xffffffff);
        setColor(IDENTIFIER_VAR, 0xff9876aa);
        setColor(LITERAL, 0xff6a8759);
        setColor(OPERATOR, 0xffffffff);
        setColor(COMMENT, 0xff808080);
        setColor(KEYWORD, 0xffcc7832);
        setColor(WHOLE_BACKGROUND, 0xff1d1d1d);
        setColor(COMPLETION_WND_BACKGROUND, 0xff2b2b2b);
        setColor(COMPLETION_WND_CORNER, 0xff999999);
        setColor(TEXT_NORMAL, 0xffffffff);
        setColor(LINE_NUMBER_BACKGROUND, 0xff313335);
        setColor(LINE_NUMBER, 0xff606366);
        setColor(LINE_NUMBER_CURRENT, 0xff606366);
        setColor(LINE_DIVIDER, 0xff606366);
        setColor(SCROLL_BAR_THUMB, 0xffa6a6a6);
        setColor(SCROLL_BAR_THUMB_PRESSED, 0xff565656);
        setColor(SELECTED_TEXT_BACKGROUND, 0xff3676b8);
        setColor(MATCHED_TEXT_BACKGROUND, 0xff32593d);
        setColor(CURRENT_LINE, 0xff323232);
        setColor(SELECTION_INSERT, 0xffffffff);
        setColor(SELECTION_HANDLE, 0xffffffff);
        setColor(BLOCK_LINE, 0xff575757);
        setColor(BLOCK_LINE_CURRENT, 0xdd575757);
        setColor(NON_PRINTABLE_CHAR, 0xffdddddd);
        setColor(TEXT_SELECTED, 0xffffffff);
        setColor(HIGHLIGHTED_DELIMITERS_FOREGROUND, 0xffffffff);
    }
}
