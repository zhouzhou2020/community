package com.hcven.community.web.mail.impl;

import com.hcven.community.web.mail.MailService;
import com.hcven.system.entity.Mail;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author zhanghao
 * @since 2019-03-13
 */
@Service
public class MailServiceImpl implements MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Resource
    @Qualifier("javaMailSender")
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送简单邮件
     */
    @Override
    public void sendSimpleMail(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getText());
        message.setCc(mail.getCc());
        mailSender.send(message);
    }

    /**
     * 发送静态资源  一张照片
     *
     * @param mail
     * @throws Exception
     */
    @Override
    public void sendInlineMail(Mail mail) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText("<html><body><img src=\"cid:chuchen\" ></body></html>", true);

            FileSystemResource file = new FileSystemResource(new File("C:\\Users\\Administrator\\Desktop\\设计图\\已完成\\微信图片_20180323135358.png"));
            // addInline函数中资源名称chuchen需要与正文中cid:chuchen对应起来
            helper.addInline("chuchen", file);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            logger.error("发送邮件发生异常");
        }

    }

    /**
     * 发送模板邮件
     *
     * @param mail
     */
    @Override
    public void sendTemplateMail(Mail mail) {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            //读取 html 模板
            freemarker.template.Configuration cfg = getConfiguration();
            Template template = cfg.getTemplate(mail.getTemplateName() + ".ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getTemplateModel());
            helper.setText(html, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }

    private Configuration getConfiguration() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setClassForTemplateLoading(this.getClass(), "/templates/mail");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }
}
