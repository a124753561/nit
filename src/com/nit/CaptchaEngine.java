/**
 *====================================================
 * 文件名称: CaptchaEngine.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2014年12月18日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit;

import java.awt.Color;
import java.awt.Font;

import org.springframework.core.io.ClassPathResource;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.FileReaderRandomBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

/**
 * @ClassName: CaptchaEngine
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2014年12月18日 下午2:52:51
 */
public class CaptchaEngine extends ListImageCaptchaEngine {

	private static final int IMAGE_WIDTH = 80;

	private static final int IMAGE_HEIGHT = 28;

	private static final int MIN_FONT_SIZE = 12;

	private static final int MAX_FONT_SIZE = 16;

	private static final int MIN_WORD_LENGTH = 4;

	private static final int MAX_WORD_LENGTH = 4;

	private static final String CHAR_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZSZHZOZPZXZXZ";

	private static final String BACKGROUND_IMAGE_PATH = "/com/nit/captcha/";

	private static final Font[] FONTS = { new Font("nyala", 1, 16), new Font("Arial", 1, 16), new Font("nyala", 1, 16), new Font("Bell", 1, 16),
			new Font("Bell MT", 1, 16), new Font("Credit", 1, 16), new Font("valley", 1, 16), new Font("Impact", 1, 16) };

	private static final Color[] COLORS = { new Color(255, 255, 255), new Color(255, 220, 220), new Color(220, 255, 255), new Color(220, 220, 255),
			new Color(255, 255, 220), new Color(220, 255, 220) };

	protected void buildInitialFactories() {
		FontGenerator fontGenerator = new RandomFontGenerator(Integer.valueOf(MIN_FONT_SIZE), Integer.valueOf(MAX_FONT_SIZE), FONTS);
		BackgroundGenerator backgroundGenerator = new FileReaderRandomBackgroundGenerator(Integer.valueOf(IMAGE_WIDTH), Integer.valueOf(IMAGE_HEIGHT), new ClassPathResource(
				BACKGROUND_IMAGE_PATH).getPath());
		TextPaster textPaster = new DecoratedRandomTextPaster(Integer.valueOf(MIN_WORD_LENGTH), Integer.valueOf(MAX_WORD_LENGTH), new RandomListColorGenerator(COLORS),
				new TextDecorator[0]);
		addFactory(new GimpyFactory(new RandomWordGenerator(CHAR_STRING), new ComposedWordToImage(fontGenerator, backgroundGenerator, textPaster)));
	}

}
