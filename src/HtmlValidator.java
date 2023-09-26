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
	/*
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
		Stack<HtmlTag> htmlStacks = new Stack<>();
		Stack<HtmlTag> htmlTags = new Stack<>();
		htmlTags.addAll(tags);
		for (HtmlTag tag : tags) {
			if (tag.isOpenTag()){
				if (htmlStacks.isEmpty()){
					htmlStacks.push(tag);
				}else{
					for (HtmlTag tagStack: htmlStacks) {
						if (!tagStack.matches(tag)){
							htmlStacks.push(tag);
							break;
						}
					}
				}

			}else{
				if (!tag.isSelfClosing()){
					if(!htmlStacks.isEmpty()){
						if (htmlStacks.peek().matches(tag)){
							htmlStacks.pop();
						}else{
							Stack<HtmlTag> tagsCoincididos = new Stack<>();
							for (HtmlTag tagStack: htmlStacks) {
								if (tagStack.matches(tag)){
										tagsCoincididos.push(tag);
								}
							}
							if (!tagsCoincididos.isEmpty()){
								return htmlStacks;
							}
						}
					}else{
						return null;
					}
				}
			}
		}
		return htmlStacks; // this line is here only so this code will compile if you don't modify it
	}
	 */

}
