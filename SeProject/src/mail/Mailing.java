package mail;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailing {
    static final String FROM = "viniyoon1006@gmail.com";
    static final String FROMNAME = "viniyoon";
    static final String TO = "sjy019254@naver.com";
    static final String SMTP_USERNAME = "viniyoon1006";
    static final String SMTP_PASSWORD = "dbsqls2382#@!#@!";
    static final String HOST = "smtp.gmail.com";
    static final int PORT = 587;
    
    static final String SUBJECT = "당신은 사랑받기위해 태어난 사람";
    
    static final String BODY = String.join(
        System.getProperty("line.separator"),
        "<h1>사용하고 계신 계정의 비밀번호는 다음과 같습니다.</h1>",
        "<p></p>."
    );
    public static void main(String[] args) throws Exception {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT); 
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject(SUBJECT);
        msg.setContent(BODY, "text/html;charset=euc-kr");
        
        Transport transport = session.getTransport();
        try {
            System.out.println("Sending...");
            
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            transport.close();
        }
    }
}