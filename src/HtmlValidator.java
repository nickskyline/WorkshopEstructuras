import java.util.Queue;
import java.util.Stack;

public class HtmlValidator {
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
		Stack<HtmlTag> htmlStacks = new Stack<>();

		for (HtmlTag tag : tags) {
			if (tag.isOpenTag()) {
				if (htmlStacks.isEmpty() || !htmlStacks.peek().matches(tag)) {
					htmlStacks.push(tag);
				}
			} else if (!tag.isSelfClosing()) {
				if (htmlStacks.isEmpty()) {
					return null;
				}
				if (htmlStacks.peek().matches(tag)) {
					htmlStacks.pop();
				} else {
					Stack<HtmlTag> tagsCoincididos = new Stack<>();
					for (HtmlTag tagStack : htmlStacks) {
						if (tagStack.matches(tag)) {
							tagsCoincididos.push(tag);
						}
					}
					if (!tagsCoincididos.isEmpty()) {
						return htmlStacks;
					}
				}
			}
		}
		return htmlStacks;
	}
}
