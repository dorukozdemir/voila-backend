package com.viola.backend.voilabackend.externals;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("emailSenderService")
public class EmailSenderService {
    
    @Autowired
    private JavaMailSender javaMailSender;

    private final String DOMAINURL = "https://voilacard.com/";
    private final String RESET_PATH = "cardvisit-dashboard/reset-my-password/";
    public boolean sendForgotPasswordEmail(String name, String emailAddress, String token) {
        String resetLink = DOMAINURL + RESET_PATH + token;
        MimeMessage email = resetPasswordEmail(name, emailAddress, resetLink);
        javaMailSender.send(email);
        return true;
    }

    public void sendSimpleMessage(
        String to, String subject, String text) {
            System.out.println("Göndermeye başlanıyor");
            SimpleMailMessage message = new SimpleMailMessage(); 
            message.setFrom("api@e.voilacard.com");
          message.setTo(to); 
          message.setSubject(subject); 
          message.setText(text);
          javaMailSender.send(message);
    }

    public void sendMimeMessage() {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String htmlMsg = "<h3>Hello World!</h3>";
        //String htmlMsg = forgotMessage();
        //mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
        // Use this or above line.
        try {
            helper.setText(htmlMsg, true);
            helper.setTo("dorukozdemir@gmail.com");
            helper.setSubject("This is the test message for testing gmail smtp server using spring mail");
            helper.setFrom("api@e.voilacard.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        } 
  
        javaMailSender.send(mimeMessage);
    }

    public String forgotMessage(String name, String resetLink) {
        String message = "<!doctype html> "+
        "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\"" +
        "  xmlns:o=\"urn:schemas-microsoft-com:office:office\"> " +
        "<head> " +
        "  <title>Şifrenizi sıfırlamak için bağlantıya tıklayabilirsiniz. | VOILA Kart</title> " +
        "  <!--[if !mso]><!-- --> "+
        "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">" +
        "  <!--<![endif]-->" +
        "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
        "  <meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">" +
        "  <style type=\"text/css\">" + 
        "    #outlook a {" +
        "      padding: 0;" +
        "    }" +
        "    body {" +
        "      margin: 0;" +
        "      padding: 0;" +
        "      -webkit-text-size-adjust: 100%;" +
        "      -ms-text-size-adjust: 100%;" +
        "    }" + 
        "    table," +
        "    td {" +
        "      border-collapse: collapse;" +
        "      mso-table-lspace: 0pt;" +
        "      mso-table-rspace: 0pt;" +
        "    }"  +
        "    img {"+
        "      border: 0;"+
        "      height: auto;"+
        "      line-height: 100%;"+
        "      outline: none;"+
        "      text-decoration: none;"+
        "      -ms-interpolation-mode: bicubic;"+
        "    }"+
        "    p {"+
        "      display: block;"+
        "      margin: 13px 0;"+
        "    }"+
        "  </style>"+
        "  <!--[if mso]>"+
        "        <xml>"+
        "        <o:OfficeDocumentSettings>"+
        "          <o:AllowPNG/>"+
        "          <o:PixelsPerInch>96</o:PixelsPerInch>"+
        "        </o:OfficeDocumentSettings>"+
        "        </xml>"+
        "        <![endif]-->"+
        "  <!--[if lte mso 11]>"+
        "        <style type=\"text/css\">"+
        "          .mj-outlook-group-fix { width:100% !important; }"+
        "        </style>"+
        "        <![endif]-->"+
        "  <!--[if !mso]><!-->"+
        "  <link href=\"https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;700&display=swap\" rel=\"stylesheet\" type=\"text/css\">"+
        "  <style type=\"text/css\"> " +
          "@import url(https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;700&display=swap);"+
        "  </style>"+
        "  <!--<![endif]-->"+
        "  <style type=\"text/css\">"+
        "    @media only screen and (min-width:480px) {"+
        "      .mj-column-per-100 {"+
        "        width: 100% !important;"+
        "        max-width: 100%;"+
        "      }"+
        "      .mj-column-per-25 {"+
        "        width: 25% !important;"+
        "        max-width: 25%;"+
        "      }" +
        "      .mj-column-per-20 {" +
        "        width: 20% !important;" +
        "        max-width: 20%;"+
        "      }"+
        "      .mj-column-per-55 {" +
        "        width: 55% !important;" +
        "        max-width: 55%;" +
        "      };" +
        "      .mj-column-per-33-333333333333336 {" +
        "        width: 33.333333333333336% !important;"+
        "        max-width: 33.333333333333336%;"+
        "      }" +
        "      .mj-column-px-10 {" +
        "        width: 10px !important;" +
        "        max-width: 10px;" +
        "      }" +
        "      .mj-column-px-318 {" +
        "        width: 318px !important;" +
        "        max-width: 318px;" +
        "      }" + 
        "      .mj-column-per-14 {" +
        "        width: 14% !important;" +
        "        max-width: 14%;" +
        "      }" +
        "      .mj-column-per-1 {" +
        "        width: 1% !important;" +
        "        max-width: 1%;" +
        "      }" +
        "    }" +
          "</style>" +
        "  <style type=\"text/css\">" +
        "    @media only screen and (max-width:480px) {" +
        "      table.mj-full-width-mobile {" +
        "        width: 100% !important;" +
        "      }"  +
        "      td.mj-full-width-mobile {" +
        "        width: auto !important;" +
        "      }" +
        "    }"+
        "    noinput.mj-menu-checkbox {" +
        "      display: block !important;" +
        "      max-height: none !important;" + 
        "      visibility: visible !important;" +
        "    }" +
        "    @media only screen and (max-width:480px) {" +
        "      .mj-menu-checkbox[type=\"checkbox\"]~.mj-inline-links {" +
        "        display: none !important;" +
        "      }" +
        "      .mj-menu-checkbox[type=\"checkbox\"]:checked~.mj-inline-links," +
        "      .mj-menu-checkbox[type=\"checkbox\"]~.mj-menu-trigger {" +
        "        display: block !important;" +
        "        max-width: none !important;" +
        "        max-height: none !important;" +
        "        font-size: inherit !important;" +
        "      }" +
        "      .mj-menu-checkbox[type=\"checkbox\"]~.mj-inline-links>a {" +
        "        display: block !important;" +
        "      }"  +
        "      .mj-menu-checkbox[type=\"checkbox\"]:checked~.mj-menu-trigger .mj-menu-icon-close {" +
        "        display: block !important;" +
        "      }" +
        "      .mj-menu-checkbox[type=\"checkbox\"]:checked~.mj-menu-trigger .mj-menu-icon-open {" +
        "        display: none !important;" +
        "      }" +
        "    }" +
        "  </style>" +
        "</head> "+
        "<body style=\"background-color:#f3f4f8;\">" +
        "  <div style=\"background-color:#f3f4f8;\"> "+
        "    <!-- Header 6 -->" +
        "    <!--[if mso | IE]><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\" ><tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\"><![endif]-->" +
        "    <div style=\"background:#212529;background-color:#212529;margin:0px auto;max-width:600px;\">" +
        "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"" +
        "        style=\"background:#212529;background-color:#212529;width:100%;\">" +
        "        <tbody>" +
        "          <tr>" +
        "            <td style=\"direction:ltr;font-size:0px;padding:0px;padding-left:20px;padding-right:20px;text-align:center;\">" +
        "              <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td class=\"\" width=\"600px\" ><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:560px;\" width=\"560\" ><tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\"><![endif]-->" +
        "              <div style=\"margin:0px auto;max-width:560px;\">" +
        "                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"" +
        "                  style=\"width:100%;\">" +
        "                  <tbody>" +
        "                    <tr>" +
        "                      <td style=\"direction:ltr;font-size:0px;padding:0px;text-align:center;\">" +
        "                        <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td class=\"\" style=\"vertical-align:top;width:560px;\" ><![endif]-->" +
        "                        <div class=\"mj-column-per-100 mj-outlook-group-fix\"" +
        "                          style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">" +
        "                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                            <tbody>" +
        "                              <tr>"+ 
        "                                <td style=\"vertical-align:top;padding:0px;\">" +
        "                                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                                    <tr>" +
        "                                      <td style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
        "                                        <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td height=\"20\" style=\"vertical-align:top;height:20px;\"><![endif]-->" +
        "                                        <div style=\"height:20px;\">&nbsp;</div>" +
        "                                        <!--[if mso | IE]></td></tr></table><![endif]--> " +
        "                                      </td>" +
        "                                   </tr>" +
        "                                  </table>" +
        "                                </td>" +
        "                              </tr>" +
        "                           </tbody>" +
        "                          </table>" +
        "                        </div>" +
        "                        <!--[if mso | IE]></td></tr></table><![endif]-->" +
        "                      </td>" +
        "                    </tr>" +
        "                  </tbody>" +
        "                </table>" +
        "              </div>" +
        "              <!--[if mso | IE]></td></tr></table></td></tr><tr><td class=\"\" width=\"600px\" ><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:560px;\" width=\"560\" ><tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\"><![endif]-->" +
        "              <div style=\"margin:0px auto;max-width:560px;\">" +
        "                 <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"" +
        "                  style=\"width:100%;\">" +
        "                  <tbody>" +
        "                    <tr>" +
        "                      <td style=\"direction:ltr;font-size:0px;padding:0px;text-align:center;\">" +
        "                        <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td class=\"\" style=\"vertical-align:top;width:140px;\" ><![endif]-->" +
        "                        <div class=\"mj-column-per-25 mj-outlook-group-fix\"" +
        "                          style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">" +
        "                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\"> " +
        "                            <tbody>" +
        "                              <tr>" +
        "                                <td style=\"vertical-align:top;padding:0px;\">" +
        "                                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                                    <tr>" +
        "                                      <td align=\"center\" style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
        "                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"" +
        "                                          style=\"border-collapse:collapse;border-spacing:0px;\">" +
        "                                          <tbody>" +
        "                                            <tr>" +
        "                                              <td style=\"width:115px;\"><img alt=\"\" height=\"auto\"" +
        "                                                  src=\"https://voilacard.com/mail/logo-2-white.png\"" +
        "                                                  style=\"border:0;display:block;outline:none;text-decoration:none;height:auto;width:100%;font-size:13px;\"" + 
        "                                                  width=\"115\"></td>" +
        "                                            </tr>" +
        "                                          </tbody>" +
        "                                        </table>" +
        "                                      </td>"+
        "                                    </tr>" +
        "                                  </table>" +
        "                                </td>" +
        "                              </tr>" +
        "                            </tbody>" +
        "                          </table>" +
        "                        </div>" +
        "                        <!--[if mso | IE]></td><td class=\"\" style=\"vertical-align:top;width:112px;\" ><![endif]-->" +
        "                        <div class=\"mj-column-per-20 mj-outlook-group-fix\"" +
        "                          style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">" +
        "                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                            <tbody>" +
        "                              <tr>" +
        "                                <td style=\"vertical-align:top;padding:0px;\">" +
        "                                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                                  </table>" +
        "                                </td>" +
        "                              </tr>" +
        "                            </tbody>" +
        "                          </table>" +
        "                        </div>" +
        "                        <!--[if mso | IE]></td><td class=\"\" style=\"vertical-align:middle;width:308px;\" ><![endif]-->" +
        "                        <div class=\"mj-column-per-55 mj-outlook-group-fix\"" +
        "                          style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:middle;width:100%;\">" +
        "                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                            <tbody>" +
        "                              <tr>" +
        "                                <td style=\"vertical-align:middle;padding:0px;padding-top:3px;\">" +
        "                                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                                    <tr>" +
        "                                      <td align=\"center\" style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
        "                                        <!--[if !mso><!--> <input type=\"checkbox\" id=\"f7a84a83773e5a08\"" +
        "                                          class=\"mj-menu-checkbox\"" +
        "                                          style=\"display:none !important; max-height:0; visibility:hidden;\">" +
        "                                        <!--<![endif]-->" +
        "                                        <div class=\"mj-menu-trigger\"" +
        "                                          style=\"display:none;max-height:0px;max-width:0px;font-size:0px;overflow:hidden;\">" +
        "                                          <label for=\"f7a84a83773e5a08\" class=\"mj-menu-label\"" + 
        "                                            style=\"display:block;cursor:pointer;mso-hide:all;-moz-user-select:none;user-select:none;color:#ffffff;font-size:30px;font-family:DM Sans, Helvetica, Arial, sans-serif;text-transform:uppercase;text-decoration:none;line-height:30px;padding:10px;\"" +
        "                                            align=\"center\"><span class=\"mj-menu-icon-open\" style=\"mso-hide:all;\">&#9776;" +
        "                                            </span><span class=\"mj-menu-icon-close\"" +
        "                                              style=\"display:none;mso-hide:all;\">&#8855;</span></label></div>" +
        "                                      </td>" +
        "                                    </tr>" +
        "                                  </table>" +
        "                                </td>" +
        "                              </tr>" +
        "                            </tbody>" +
        "                          </table>" +
        "                        </div>" +
        "                        <!--[if mso | IE]></td></tr></table><![endif]-->" +
        "                      </td>" +
        "                    </tr>" +
        "                  </tbody>" +
        "                </table>" +
        "              </div>" +
        "              <!--[if mso | IE]></td></tr></table></td></tr><tr><td class=\"\" width=\"600px\" ><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:560px;\" width=\"560\" ><tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\"><![endif]-->" +
        "              <div style=\"margin:0px auto;max-width:560px;\">" +
        "                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"" +
        "                  style=\"width:100%;\">" +
        "                  <tbody>" +
        "                    <tr>" +
        "                      <td style=\"direction:ltr;font-size:0px;padding:0px;text-align:center;\">" +
        "                        <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td class=\"\" style=\"vertical-align:top;width:560px;\" ><![endif]-->" +
        "                        <div class=\"mj-column-per-100 mj-outlook-group-fix\"" +
        "                          style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">" +
        "                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                            <tbody>" +
        "                              <tr>" +
        "                                <td style=\"vertical-align:top;padding:0px;\">" +
        "                                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                                    <tr>" +
        "                                      <td style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
        "                                        <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td height=\"20\" style=\"vertical-align:top;height:20px;\"><![endif]-->" +
        "                                        <div style=\"height:20px;\">&nbsp;</div>" +
        "                                        <!--[if mso | IE]></td></tr></table><![endif]-->" +
        "                                      </td>" +
        "                                    </tr>" +
        "                                  </table>" +
        "                                </td>" +
        "                              </tr>" +
        "                            </tbody>" +
        "                          </table>" +
        "                        </div>" +
        "                        <!--[if mso | IE]></td></tr></table><![endif]-->" +
        "                      </td>" +
        "                    </tr>" +
        "                  </tbody>" +
        "                </table>" +
        "              </div>" +
        "              <!--[if mso | IE]></td></tr></table></td></tr></table><![endif]-->" +
        "            </td>" +
        "          </tr>" +
        "        </tbody>" +
        "      </table>" +
        "    </div>" +
        "    <!--[if mso | IE]></td></tr></table><![endif]-->" +
        "    <!-- Header 6 -End -->" +
        "    <!-- Content/ 9 -->" +
        "    <!--[if mso | IE]><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\" ><tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\"><![endif]-->" +
        "    <div style=\"background:#212529;background-color:#212529;margin:0px auto;max-width:600px;\">" +
        "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"" +
        "        style=\"background:#212529;background-color:#212529;width:100%;\">" +
        "        <tbody>" +
        "          <tr>" +
        "            <td style=\"direction:ltr;font-size:0px;padding:36px 20px;text-align:center;\">" +
        "              <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td class=\"\" style=\"vertical-align:top;width:560px;\" ><![endif]-->" +
        "              <div class=\"mj-column-per-100 mj-outlook-group-fix\"" +
        "                style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">" +
        "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                  <tbody>" +
        "                    <tr>" +
        "                      <td style=\"vertical-align:top;padding:0px;\">" +  
        "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" + 
        "                          <tr>" + 
        "                            <td align=\"center\" style=\"font-size:0px;padding:0px;word-break:break-word;\">" + 
        "                              <div"+ 
        "                                style=\"font-family:DM Sans,Helvetica,Arial,sans-serif;font-size:24px;font-weight:normal;letter-spacing:-0.75px;line-height:32px;text-align:center;color:#212529;\">" +
        "                                <p style=\"Margin:0;color:#ffffff;\">Şifrenizi sıfırlamak için bağlantıya tıklayabilirsiniz.</p>" + 
        "                              </div>" +
        "                            </td>" +
        "                          </tr>" +
        "                        </table>" +
        "                      </td>" +
        "                    </tr>" +
        "                  </tbody>" +
        "                </table>" +
        "              </div>" +
        "              <!--[if mso | IE]></td></tr></table><![endif]-->" +
        "            </td>" +
        "          </tr>" +
        "        </tbody>" +
        "      </table>" +
        "    </div>" +
        "    <!--[if mso | IE]></td></tr></table><![endif]-->" +
        "    <!-- Content/ 9 -End -->" +
        "    <!--[if mso | IE]><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\" ><tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\"><![endif]-->" +
        "    <div style=\"background:#ffffff;background-color:#ffffff;margin:0px auto;max-width:600px;\">" +
        "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"" +
        "        style=\"background:#ffffff;background-color:#ffffff;width:100%;\">" +
        "        <tbody>" +
        "          <tr>" +
        "            <td style=\"direction:ltr;font-size:0px;padding:0px;padding-top:28px;text-align:center;\">" +
        "              <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr></tr></table><![endif]-->" +
        "            </td>" +
        "          </tr>" +
        "        </tbody>" +
        "      </table>" +
        "    </div>" +
        "    <!--[if mso | IE]></td></tr></table><![endif]-->" +
        "    <!-- Content/ 17 -->" +
        "    <!--[if mso | IE]><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\" ><tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\"><![endif]-->" +
        "    <div style=\"background:#ffffff;background-color:#ffffff;margin:0px auto;max-width:600px;\">" +
        "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\""+
        "        style=\"background:#ffffff;background-color:#ffffff;width:100%;\">" +
        "        <tbody>" +
        "          <tr>" +
        "            <td style=\"direction:ltr;font-size:0px;padding:8px 20px;text-align:center;\">" +
        "              <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td class=\"\" style=\"vertical-align:top;width:560px;\" ><![endif]-->" +
        "              <div class=\"mj-column-per-100 mj-outlook-group-fix\" "+
        "                style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">" +
        "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                  <tbody>" +
        "                    <tr>" +
        "                      <td style=\"vertical-align:top;padding:0px;\">" +
        "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                          <tr>" +
        "                            <td align=\"center\" style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
        "                              <div" +
        "                                style=\"font-family:DM Sans,Helvetica,Arial,sans-serif;font-size:20px;font-weight:normal;letter-spacing:-0.75px;line-height:32px;text-align:center;color:#000000;\">" +
        "                                <p style=\"Margin:0;\">Merhaba " + name + ", şifrenizi sıfırlamak için bağlantıya tıklayabilirsiniz. Eğer bu işlemi siz yapmadıysanız bizimle iletişime geçebilirsiniz.</p>" +
        "                              </div>" +
        "                            </td>" +
        "                          </tr>" +
        "                        </table>" +
        "                      </td>" +
        "                    </tr>" +
        "                  </tbody>" +
        "                </table>" +
        "              </div>" +
        "              <!--[if mso | IE]></td></tr></table><![endif]-->" +
        "            </td>" +
        "          </tr>" +
        "        </tbody>" +
        "      </table>" +
        "    </div>" + 
        "    <!--[if mso | IE]></td></tr></table><![endif]-->" +
        "    <!-- Content/ 17 -End -->" +
        "    <!-- Content/ 12 -->" +
        "    <!--[if mso | IE]><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\" ><tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\"><![endif]-->" +
        "    <div style=\"background:#ffffff;background-color:#ffffff;margin:0px auto;max-width:600px;\">" +
        "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"" +
        "        style=\"background:#ffffff;background-color:#ffffff;width:100%;\">" +
        "        <tbody>" +
        "          <tr>" +
        "            <td style=\"direction:ltr;font-size:0px;padding:16px 20px 28px;text-align:center;\">" +
        "              <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td class=\"\" width=\"600px\" ><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:560px;\" width=\"560\" ><tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\"><![endif]-->" +
        "              <div style=\"margin:0px auto;max-width:560px;\">" +
        "                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"" +
        "                  style=\"width:100%;\">" +
        "                  <tbody>" +
        "                    <tr>" +
        "                      <td style=\"direction:ltr;font-size:0px;padding:0px;text-align:center;\">" +
        "                        <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td class=\"\" style=\"vertical-align:top;width:186.66666666666669px;\" ><![endif]-->" +
        "                        <div class=\"mj-column-per-33-333333333333336 mj-outlook-group-fix\"" +
        "                          style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">" +
        "                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                            <tbody>" +
        "                              <tr>" +
        "                                <td style=\"vertical-align:top;padding:0px;\">" +
        "                                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                                    <tr>"+ 
        "                                      <td align=\"center\" vertical-align=\"middle\"" +
        "                                        style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
        "                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"" +
        "                                          style=\"border-collapse:separate;width:173px;line-height:100%;\">" +
        "                                          <tr>" +
        "                                            <td align=\"center\" bgcolor=\"#007bff\" role=\"presentation\"" +
        "                                              style=\"border:none;border-radius:4px;cursor:auto;height:44px;mso-padding-alt:10px 25px;text-align:center;background:#007bff;\"" +
        "                                              valign=\"middle\"><a href=\"" + resetLink + "\"" +
        "                                                style=\"display: inline-block; width: 123px; background: #007bff; color: #FFFFFF; font-family:DM Sans,Helvetica,Arial,sans-serif; font-size: 16px; font-weight: normal; line-height: 18px; letter-spacing: 0px; margin: 0; text-decoration: none; text-transform: none; padding: 10px 25px; mso-padding-alt: 0px; border-radius: 4px;\" " + 
        "                                                target=\"_blank\">Şifremi Sıfırla</a></td>" +
        "                                          </tr>" +
        "                                        </table>" +
        "                                      </td>" +
        "                                    </tr>" +
        "                                  </table>" +
        "                                </td>" +
        "                              </tr>" +
        "                            </tbody>" +
        "                          </table>" +
        "                        </div>" +
        "                      </td>" +
        "                    </tr>" +
        "                  </tbody>" +
        "                </table>" +
        "              </div>" +
        "              <!--[if mso | IE]></td></tr></table></td></tr></table><![endif]-->" +
        "            </td>" +
        "          </tr>" +
        "        </tbody>" +
        "      </table>" +
        "    </div>" +
        "    <!--[if mso | IE]></td></tr></table><![endif]-->" +
        "    <!-- Content/ 12 -End -->" +
        "    <!-- Footer 1 -->" +
        "    <!--[if mso | IE]><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\" ><tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\"><![endif]-->" +
        "    <div style=\"background:#ffffff;background-color:#ffffff;margin:0px auto;max-width:600px;\">" +
        "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"" +
        "        style=\"background:#ffffff;background-color:#ffffff;width:100%;\">" +
        "        <tbody>" +
        "          <tr>" +
        "            <td style=\"direction:ltr;font-size:0px;padding:0px 20px 28px;text-align:center;\">" +
        "              <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td class=\"\" width=\"600px\" ><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:560px;\" width=\"560\" ><tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\"><![endif]-->" +
        "              <div style=\"margin:0px auto;max-width:560px;\">" +
        "                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"" +
        "                  style=\"width:100%;\">" +
        "                  <tbody>" +
        "                    <tr>" +
        "                      <td style=\"direction:ltr;font-size:0px;padding:0px;text-align:center;\">" +
        "                        <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td class=\"\" style=\"vertical-align:top;width:560px;\" ><![endif]-->" +
        "                        <div class=\"mj-column-per-100 mj-outlook-group-fix\"" +
        "                          style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">" +
        "                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                            <tbody>" +
        "                              <tr>" +
        "                                <td style=\"vertical-align:top;padding:0px;\">" +
        "                                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                                    <tr>" +
        "                                      <td style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
        "                                        <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td height=\"28\" style=\"vertical-align:top;height:28px;\"><![endif]-->" +
        "                                        <div style=\"height:28px;\">&nbsp;</div>" +
        "                                        <!--[if mso | IE]></td></tr></table><![endif]-->" +
        "                                      </td>" +
        "                                    </tr>" +
        "                                    <tr>" +
        "                                      <td align=\"center\" style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
        "                                        <div" +
        "                                          style=\"font-family:DM Sans,Helvetica,Arial,sans-serif;font-size:24px;font-weight:normal;letter-spacing:-0.75px;line-height:32px;text-align:center;color:#000000;\">" +
        "                                          <p style=\"Margin:0;\">Bizi Takip Edin!</p>" +
        "                                        </div>" +
        "                                      </td>" +
        "                                    </tr>" +
        "                                  </table>" +
        "                                </td>" +
        "                              </tr>" +
        "                            </tbody>" +
        "                          </table>" +
        "                        </div>" +
        "                        <!--[if mso | IE]></td></tr></table><![endif]-->" +
        "                      </td>" +
        "                    </tr>" +
        "                  </tbody>" +
        "                </table>" +
        "              </div>" +
        "              <!--[if mso | IE]></td></tr></table></td></tr><tr><td class=\"\" width=\"600px\" ><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:560px;\" width=\"560\" ><tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\"><![endif]-->" +
        "              <div style=\"margin:0px auto;max-width:560px;\">" +
        "                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"" +
        "                  style=\"width:100%;\">" +
        "                  <tbody>" +
        "                    <tr>" +
        "                      <td style=\"direction:ltr;font-size:0px;padding:0px;text-align:center;\">" +
        "                        <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td class=\"\" style=\"vertical-align:top;width:318px;\" ><![endif]-->" +
        "                        <div class=\"mj-column-px-318 mj-outlook-group-fix\"" +
        "                          style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">" +
        "                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                            <tbody>" +
        "                              <tr>" +
        "                                <td style=\"vertical-align:top;padding:0px;\">" +
        "                                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                                    <tr>" +
        "                                      <td style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
        "                                        <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td height=\"16\" style=\"vertical-align:top;height:16px;\"><![endif]-->" +
        "                                        <div style=\"height:16px;\">&nbsp;</div>" +
        "                                        <!--[if mso | IE]></td></tr></table><![endif]-->" +
        "                                      </td>" +
        "                                    </tr>" +
        "                                    <tr>" +
        "                                      <td align=\"left\" style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
        "                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\"" +
        "                                          style=\"color:#000000;font-family:DM Sans, Helvetica, Arial, sans-serif;font-size:13px;line-height:22px;table-layout:auto;width:100%;border:none;\">" +
        "                                          <tr>" +
        "                                            <td valign=\"middle\"><a href=\"https://www.instagram.com/voilacard\" target=\"_blank\"" +
        "                                                style=\"text-decoration: none; color: inherit; border-bottom: none;\"><img" +
        "                                                  src=\"https://voilacard.com/mail/instagramBlack.png\" alt=\"Instagram\" id=\"social\"" +
        "                                                  style=\"width: 23.33px;\"></a></td>" +
        "                                            <td width=\"20%\" style=\"width: 20%; max-width: 20%; min-width: 20%\">&nbsp;" +
        "                                            </td>" +
        "                                            <td valign=\"middle\" align=\"center\"><a href=\"https://www.facebook.com/Voila-100120038965630\" target=\"_blank\"" +
        "                                                style=\"text-decoration: none; color: inherit; border-bottom: none;\"><img" +
        "                                                  src=\"https://voilacard.com/mail/facebookBlack.png\" alt=\"Facebook\" id=\"social-facebook\"" +
        "                                                  style=\"height: 23.33px;\"></a></td>" +
        "                                            <td width=\"25%\" style=\"width: 25%px; max-width: 25%; min-width: 25%;\">&nbsp;" +
        "                                            </td>" +
        "                                            <td valign=\"middle\" align=\"center\"><a href=\"https://twitter.com/voilacard\" target=\"_blank\"" +
        "                                                style=\"text-decoration: none; color: inherit; border-bottom: none;\"><img" +
        "                                                  src=\"https://voilacard.com/mail/twitterBlack.png\" alt=\"Twitter\" id=\"social-twitter\"" +
        "                                                  style=\"width: 24.41px;\"></a></td>" +
        "                                            <td width=\"20%\" style=\"width: 20%; max-width: 20%; min-width: 20%\">&nbsp;" +
        "                                            </td>" +
        "                                            <td valign=\"middle\" align=\"right\"><a href=\"https://www.linkedin.com/company/voilacard/\" target=\"_blank\"" + 
        "                                                style=\"text-decoration: none; color: inherit; border-bottom: none;\"><img" +
        "                                                  src=\"https://voilacard.com/mail/linkedinBlack.png\" alt=\"LinkedIn\" id=\"social-pinterest\"" +
        "                                                  style=\"width: 23.33px;\"></a></td>" +
        "                                            <td width=\"20%\" style=\"width: 20%; max-width: 20%; min-width: 20%\">&nbsp;" +
        "                                            </td>" +
        "                                            <td valign=\"middle\" align=\"right\"><a href=\"https://vm.tiktok.com/ZSeRhQWeB/\" target=\"_blank\"" +
        "                                                style=\"text-decoration: none; color: inherit; border-bottom: none;\"><img" +
        "                                                    src=\"https://voilacard.com/mail/tiktokBlack.png\" alt=\"TikTok\" id=\"social-pinterest\"" +
        "                                                    style=\"width: 23.33px;\"></a></td>" +
        "                                            <td width=\"20%\" style=\"width: 20%; max-width: 20%; min-width: 20%\">&nbsp;" +
        "                                            </td>" +
        "                                            <td valign=\"middle\" align=\"right\"><a href=\"https://www.youtube.com/channel/UCFGtnJ8k1wa59P0e4jk1lbQ\" target=\"_blank\"" +
        "                                                style=\"text-decoration: none; color: inherit; border-bottom: none;\"><img" +
        "                                                    src=\"https://voilacard.com/mail/youtubeBlack.png\" alt=\"YouTube\" id=\"social-pinterest\"" +
        "                                                    style=\"width: 23.33px;\"></a></td>" +
        "                                          </tr>" +
        "                                        </table>" +
        "                                      </td>" +
        "                                    </tr>" +
        "                                    <tr>" +
        "                                      <td style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
        "                                        <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td height=\"28\" style=\"vertical-align:top;height:28px;\"><![endif]-->" +
        "                                        <div style=\"height:28px;\">&nbsp;</div>" +
        "                                        <!--[if mso | IE]></td></tr></table><![endif]-->" +
        "                                      </td>" +
        "                                    </tr>" +
        "                                  </table>" +
        "                                </td>" +
        "                              </tr>" +
        "                            </tbody>" +
        "                          </table>" +
        "                        </div>" +
        "                        <!--[if mso | IE]></td></tr></table><![endif]-->" +
        "                      </td>" +
        "                    </tr>" +
        "                  </tbody>" +
        "                </table>" +
        "              </div>" +
        "              <!--[if mso | IE]></td></tr></table></td></tr><tr><td class=\"\" width=\"600px\" ><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:560px;\" width=\"560\" ><tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\"><![endif]-->" +
        "              <div style=\"margin:0px auto;max-width:560px;\">" +
        "                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"" +
        "                  style=\"width:100%;\">" +
        "                  <tbody>" +
        "                    <tr>" +
        "                      <td style=\"direction:ltr;font-size:0px;padding:0px;text-align:center;\">" +
        "                        <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td class=\"\" style=\"vertical-align:top;width:560px;\" ><![endif]-->" +
        "                        <div class=\"mj-column-per-100 mj-outlook-group-fix\"" +
        "                          style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">" +
        "                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                            <tbody>" +
        "                              <tr>" +
        "                                <td style=\"vertical-align:top;padding:0px;\">" +
        "                                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                                    <tr>" +
        "                                      <td style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
        "                                        <p" +
        "                                          style=\"border-top:solid 1px #F3F4F8;font-size:1px;margin:0px auto;width:100%;\">" +
        "                                        </p>" +
        "                                        <!--[if mso | IE]><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-top:solid 1px #F3F4F8;font-size:1px;margin:0px auto;width:560px;\" role=\"presentation\" width=\"560px\" ><tr><td style=\"height:0;line-height:0;\"> &nbsp;" +
        "</td></tr></table><![endif]-->" +
        "                                      </td>" +
        "                                    </tr>" +
        "                                  </table>" +
        "                                </td>" +
        "                              </tr>" +
        "                            </tbody>" +
        "                          </table>" +
        "                        </div>" +
        "                        <!--[if mso | IE]></td></tr></table><![endif]-->" +
        "                      </td>" +
        "                    </tr>" +
        "                  </tbody>" +
        "                </table>" +
        "              </div>" +
        "              <!--[if mso | IE]></td></tr></table></td></tr><tr><td class=\"\" width=\"600px\" ><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:560px;\" width=\"560\" ><tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\"><![endif]-->" +
        "              <div style=\"margin:0px auto;max-width:560px;\">" +
        "                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"" +
        "                  style=\"width:100%;\">" +
        "                  <tbody>" +
        "                    <tr>" +
        "                      <td style=\"direction:ltr;font-size:0px;padding:0px;text-align:center;\">" +
        "                        <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td class=\"\" style=\"vertical-align:top;width:78.4px;\" ><![endif]-->" +
        "                        <div class=\"mj-column-per-14 mj-outlook-group-fix\"" +
        "                          style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">" +
        "                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                            <tbody>" +
        "                              <tr>" +
        "                                <td style=\"vertical-align:top;padding:0px;\">" +
        "                                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                                    <tr>" +
        "                                      <td style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
        "                                        <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td height=\"20\" style=\"vertical-align:top;height:20px;\"><![endif]-->" +
        "                                        <div style=\"height:20px;\">&nbsp;</div>" +
        "                                        <!--[if mso | IE]></td></tr></table><![endif]-->" +
        "                                      </td>" +
        "                                    </tr>" +
        "                                  </table>" +
        "                                </td>" +
        "                              </tr>" +
        "                            </tbody>" +
        "                          </table>" +
        "                        </div>" +
        "                        <!--[if mso | IE]></td><td class=\"\" style=\"vertical-align:top;width:5.6px;\" ><![endif]-->" +
        "                        <div class=\"mj-column-per-1 mj-outlook-group-fix\"" +
        "                          style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">" +
        "                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                            <tbody>" +
        "                              <tr>" +
        "                                <td style=\"vertical-align:top;padding:0px;padding-top:16px;\">" +
        "                                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                                    <tr>" +
        "                                      <td align=\"center\" style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
        "                                        <div" + 
        "                                          style=\"font-family:DM Sans, Helvetica, Arial, sans-serif;font-size:13px;line-height:22px;text-align:center;color:#94959A;\">" + 
        "                                          &bull;</div>" +
        "                                      </td>" +
        "                                    </tr>" +
        "                                  </table>" +
        "                                </td>" +
        "                              </tr>" +
        "                            </tbody>" +
        "                          </table>" +
        "                        </div>" +
        "                        <!--[if mso | IE]></td><td class=\"\" style=\"vertical-align:top;width:112px;\" ><![endif]-->" +
        "                        <div class=\"mj-column-per-20 mj-outlook-group-fix\"" +
        "                          style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">" +
        "                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                            <tbody>" +
        "                              <tr>" +
        "                                <td style=\"vertical-align:top;padding:0px;padding-top:16px;\">" +
        "                                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
        "                                    <tr>" +
        "                                      <td align=\"center\" style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
        "                                        <div" +
        "                                          style=\"font-family:DM Sans, Helvetica, Arial, sans-serif;font-size:13px;line-height:1;text-align:center;color:#000000;\">" +
        "                                          <a href=\"https://voilacard.com/iletisim\"" +
        "                                            style=\"font-family:DM Sans,Helvetica,Arial,sans-serif; font-weight: normal; font-size: 14px; line-height: 22px; letter-spacing: -0.02em; color: #000000; text-decoration: underline;\">Bize Ulaşın</a>" +
        "                                        </div>" +
        "                                      </td>" +
        "                                    </tr>" +
        "                                  </table>" +
        "                                </td>" +
        "                              </tr>" +
        "                            </tbody>" +
        "                          </table>" +
        "                        </div>" +
        "                        <!--[if mso | IE]></td><td class=\"\" style=\"vertical-align:top;width:5.6px;\" ><![endif]-->" +
        "                        <div class=\"mj-column-per-1 mj-outlook-group-fix\"" +
        "                          style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">" +
        "                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
    "                                <tbody>" +
    "                                  <tr>" +
    "                                    <td style=\"vertical-align:top;padding:0px;padding-top:16px;\">" +
    "                                      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
    "                                        <tr>" +
    "                                          <td align=\"center\" style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
    "                                            <div" +
    "                                              style=\"font-family:DM Sans, Helvetica, Arial, sans-serif;font-size:13px;line-height:22px;text-align:center;color:#94959A;\">" +
    "                                              &bull;</div>" +
    "                                          </td>" +
    "                                        </tr>" +
    "                                      </table>" +
    "                                    </td>" +
    "                                  </tr>" +
    "                                </tbody>" +
    "                              </table>" +
    "                            </div>" +
    "                            <!--[if mso | IE]></td><td class=\"\" style=\"vertical-align:top;width:78.4px;\" ><![endif]-->" +
    "                            <div class=\"mj-column-per-14 mj-outlook-group-fix\"" +
    "                              style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">" +
    "                              <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
    "                                <tbody>" +
    "                                  <tr>" +
    "                                    <td style=\"vertical-align:top;padding:0px;\">" +
    "                                      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
    "                                        <tr>" +
    "                                          <td style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
    "                                            <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td height=\"20\" style=\"vertical-align:top;height:20px;\"><![endif]-->" +
    "                                            <div style=\"height:20px;\">&nbsp;</div>" +
    "                                            <!--[if mso | IE]></td></tr></table><![endif]-->" +
    "                                          </td>" +
    "                                        </tr>" +
    "                                      </table>" +
    "                                    </td>" +
    "                                  </tr>" +
    "                                </tbody>" +
    "                              </table>" +
    "                            </div>" +
    "                            <!--[if mso | IE]></td></tr></table><![endif]-->" +
    "                          </td>" +
    "                        </tr>" +
    "                      </tbody>" +
    "                    </table>" +
    "                  </div>" +
    "                  <!--[if mso | IE]></td></tr></table></td></tr><tr><td class=\"\" width=\"600px\" ><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:560px;\" width=\"560\" ><tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\"><![endif]-->" +
    "                  <div style=\"margin:0px auto;max-width:560px;\">" +
    "                    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"" +
    "                      style=\"width:100%;\">" +
    "                      <tbody>" +
    "                        <tr>" +
    "                          <td style=\"direction:ltr;font-size:0px;padding:0px;text-align:center;\">" +
    "                            <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td class=\"\" style=\"vertical-align:top;width:560px;\" ><![endif]-->" +
    "                            <div class=\"mj-column-per-100 mj-outlook-group-fix\"" +
    "                              style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">" +
    "                              <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
    "                                <tbody>" +
    "                                  <tr>" +
    "                                    <td style=\"vertical-align:top;padding:0px;\">" +
    "                                     <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">" +
    "                                        <tr>" +
    "                                          <td style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
    "                                            <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td height=\"16\" style=\"vertical-align:top;height:16px;\"><![endif]-->" +
    "                                            <div style=\"height:16px;\">&nbsp;</div>" +
    "                                            <!--[if mso | IE]></td></tr></table><![endif]-->" +
    "                                          </td>" +
    "                                        </tr>" +
    "                                        <tr>" +
    "                                          <td style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
    "                                            <p" +
    "                                              style=\"border-top:solid 1px #F3F4F8;font-size:1px;margin:0px auto;width:100%;\">" +
    "                                            </p>" +
    "                                            <!--[if mso | IE]><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-top:solid 1px #F3F4F8;font-size:1px;margin:0px auto;width:560px;\" role=\"presentation\" width=\"560px\" ><tr><td style=\"height:0;line-height:0;\"> &nbsp;" +
    "    </td></tr></table><![endif]-->" +
    "                                          </td>" +
    "                                        </tr>" +
    "                                        <tr>" +
    "                                          <td style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
    "                                            <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td height=\"28\" style=\"vertical-align:top;height:28px;\"><![endif]-->" +
    "                                            <div style=\"height:28px;\">&nbsp;</div>" +
    "                                            <!--[if mso | IE]></td></tr></table><![endif]-->" +
    "                                          </td>" +
    "                                        </tr>" +
    "                                        <tr>" +
    "                                          <td align=\"center\" style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
    "                                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"" +
    "                                              style=\"border-collapse:collapse;border-spacing:0px;\">" +
    "                                              <tbody>" +
    "                                                <tr>" +
        "                                              <td style=\"width:135px;\"><img alt=\"\" height=\"auto\"" +
        "                                                  src=\"https://voilacard.com/mail/logo-2-dark.png\"" +
        "                                                  style=\"border:0;display:block;outline:none;text-decoration:none;height:auto;width:100%;font-size:13px;\"" +
        "                                                  width=\"135\"></td>" +
        "                                            </tr>" +
        "                                          </tbody>" +
        "                                        </table>" +
        "                                      </td>" +
        "                                    </tr>" +
        "                                    <tr>" +
        "                                      <td style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
        "                                        <!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td height=\"16\" style=\"vertical-align:top;height:16px;\"><![endif]-->" +
        "                                        <div style=\"height:16px;\">&nbsp;</div>" +
        "                                        <!--[if mso | IE]></td></tr></table><![endif]-->" +
        "                                      </td>" +
        "                                    </tr>" + 
        "                                    <tr>" +
        "                                      <td align=\"center\" style=\"font-size:0px;padding:0px;word-break:break-word;\">" +
        "                                        <div" +
        "                                          style=\"font-family:DM Sans,Helvetica,Arial,sans-serif;font-size:14px;font-weight:normal;letter-spacing:-0.3px;line-height:22px;text-align:center;color:#000000;\">"+
        "                                          <p style=\"Margin:0;color:#94959A;\">Voila Akıllı Kart Teknolojileri A.Ş.</p>"+ 
        "                                        </div>" +
        "                                      </td>" +
        "                                    </tr>" +
        "                                  </table>" +
        "                                </td>" +
        "                              </tr>" +
        "                            </tbody>" +
        "                          </table>" +
        "                        </div>" +
        "                        <!--[if mso | IE]></td></tr></table><![endif]-->" +
        "                      </td>" +
        "                    </tr>" +
        "                  </tbody>" +
        "                </table>" +
        "              </div>" +
        "              <!--[if mso | IE]></td></tr></table></td></tr></table><![endif]-->" +
        "            </td>" +
        "          </tr>" +
        "        </tbody>" +
              "</table>" +
            "</div> " +
            "<!--[if mso | IE]></td></tr></table><![endif]--> " + 
          "</div> " + 
        "</body> " + 
        "</html> ";


        return message;
    }

    private MimeMessage resetPasswordEmail(String name, String username, String resetLink) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String htmlMsg = forgotMessage(name, resetLink);
        //mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
        // Use this or above line.
        try {
            helper.setText(htmlMsg, true);
            helper.setTo(username);
            helper.setSubject("Şifremi Sıfırla");
            helper.setFrom("noreply@e.voilacard.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        } 
        return mimeMessage;
    }

}