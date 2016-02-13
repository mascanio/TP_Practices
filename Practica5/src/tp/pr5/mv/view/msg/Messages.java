package tp.pr5.mv.view.msg;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {

	public static final String LANGUAGE_SPANISH = "SPANISH",
			LANGUAGE_ENGLISH = "ENGLISH";

	private static String currentLanguage = LANGUAGE_ENGLISH;

	private static String BUNDLE_NAME = "tp.pr5.mv.view.msg.messages_" + currentLanguage; //$NON-NLS-1$

	private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private Messages() {
	}

	public static void setLanguae(String language) {
		if (language.equalsIgnoreCase(LANGUAGE_ENGLISH))
			currentLanguage = LANGUAGE_ENGLISH;
		else if (language.equalsIgnoreCase(LANGUAGE_SPANISH))
			currentLanguage = LANGUAGE_SPANISH;
		else
			throw new Error("INVALID_LANGUAGE");
		BUNDLE_NAME = "tp.pr5.mv.view.msg.messages_" + currentLanguage; //$NON-NLS-1$

		RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
