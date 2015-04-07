/**
 *====================================================
 * 文件名称: SettingController.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2015年1月9日			Administrator(创建:创建文件)
 *====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 * 
 */
package com.nit.controller.admin;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nit.CommonAttributes;
import com.nit.FileInfo.FileType;
import com.nit.Message;
import com.nit.Setting;
import com.nit.Setting.AccountLockType;
import com.nit.Setting.CaptchaType;
import com.nit.Setting.ConsultationAuthority;
import com.nit.Setting.ReviewAuthority;
import com.nit.Setting.RoundType;
import com.nit.Setting.StockAllocationTime;
import com.nit.Setting.WatermarkPosition;
import com.nit.service.CacheService;
import com.nit.service.FileService;
import com.nit.service.StaticService;
import com.nit.util.SettingUtils;

/**
 * @ClassName: SettingController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2015年1月9日 上午10:15:16
 */
@Controller("adminSettingController")
@RequestMapping("/admin/setting")
public class SettingController extends BaseController {

	@Resource(name = "fileServiceImpl")
	private FileService fileService;
	@Resource(name = "cacheServiceImpl")
	private CacheService cacheService;
	@Resource(name = "staticServiceImpl")
	private StaticService staticService;
	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model) {
		model.addAttribute("watermarkPositions", WatermarkPosition.values());
		model.addAttribute("roundTypes", RoundType.values());
		model.addAttribute("captchaTypes", CaptchaType.values());
		model.addAttribute("accountLockTypes", AccountLockType.values());
		model.addAttribute("stockAllocationTimes", StockAllocationTime.values());
		model.addAttribute("reviewAuthorities", ReviewAuthority.values());
		model.addAttribute("consultationAuthorities", ConsultationAuthority.values());
		return "/admin/setting/edit";

	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Setting setting, MultipartFile watermarkImageFile, RedirectAttributes redirectAttributes) {
		if (!isValid(setting)) {
			return ERROR_VIEW;
		}
		if (setting.getUsernameMinLength() > setting.getUsernameMaxLength() || setting.getPasswordMinLength() > setting.getPasswordMinLength()) {
			return ERROR_VIEW;
		}
		Setting srcSetting = SettingUtils.get();
		if (StringUtils.isEmpty(setting.getSmtpPassword())) {
			setting.setSmtpPassword(srcSetting.getSmtpPassword());
		}
		if (watermarkImageFile != null && !watermarkImageFile.isEmpty()) {
			if (!fileService.isValid(FileType.image, watermarkImageFile)) {
				addFlashMessage(redirectAttributes, Message.error("admin.upload.invalid"));
				return "redirect:edit.jhtml";
			}
			String watermarkImage = fileService.uploadLocal(FileType.image, watermarkImageFile);
			setting.setWatermarkImage(watermarkImage);
		} else {
			setting.setWatermarkImage(srcSetting.getWatermarkImage());
		}
		setting.setCnzzSiteId(srcSetting.getCnzzSiteId());
		setting.setCnzzPassword(srcSetting.getCnzzPassword());
		SettingUtils.set(setting);
		cacheService.clear();
		staticService.buildIndex();
		staticService.buildOther();
		
		OutputStream outputStream = null;
		try {
			org.springframework.core.io.Resource resource = new ClassPathResource(CommonAttributes.WIT_PROPERTIES_PATH);
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			String templateUpdateDelay = properties.getProperty("template.update_delay");
			String messageCacheSeconds = properties.getProperty("message.cache_seconds");
			if (setting.getIsDevelopmentEnabled()) {
				if (!templateUpdateDelay.equals("0") || !messageCacheSeconds.equals("0")) {
					outputStream = new FileOutputStream(resource.getFile());
					properties.setProperty("template.update_delay", "0");
					properties.setProperty("message.cache_seconds", "0");
					properties.store(outputStream, "rsico PROPERTIES");
				}
			} else {
				if (templateUpdateDelay.equals("0") || messageCacheSeconds.equals("0")) {
					outputStream = new FileOutputStream(resource.getFile());
					properties.setProperty("template.update_delay", "3600");
					properties.setProperty("message.cache_seconds", "3600");
					properties.store(outputStream, "rsico PROPERTIES");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(outputStream);
		}
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:edit.jhtml";
	}
}
