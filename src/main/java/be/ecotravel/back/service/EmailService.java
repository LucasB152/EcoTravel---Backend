package be.ecotravel.back.service;

import be.ecotravel.back.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    private final UserRepository userRepo;

    @Value("${ecotravel.frontUrl}")
    private String frontUrl;

    @Autowired
    public EmailService(
            JavaMailSender mailSender,
            UserRepository userRepo
    ) {
        this.mailSender = mailSender;
        this.userRepo = userRepo;
    }

    public void sendEmail(String from, String to, String subject, String text) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
        mail.setFrom(from);
        mail.setSubject(subject);
        mail.setText(text);
        mailSender.send(mail);
    }

    private void verifyEmail(String email) {
        userRepo.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("The email does not exist, therefor the email cannot be sent."));
    }

    public void sendConfirmationEmail(String email, String token) {
        verifyEmail(email);

        String verificationLink = frontUrl + "verify-email/" + token;

        String emailContent =
                "<html>" +
                    "<body>" +
                        "<p>Bonjour,</p>" +
                        "<p>Merci de vous être inscrit. Veuillez cliquer sur le lien ci-dessous pour vérifier votre adresse e-mail :</p>" +
                        "<p><a href=\"" + verificationLink + "\">Vérifiez votre e-mail</a></p>" +
                        "<p>Si vous ne parvenez pas à cliquer sur le lien, copiez et collez l'URL suivante dans votre navigateur :</p>" +
                        "<p>" + verificationLink + "</p>" +
                        "<p>Merci,</p>" +
                        "<p>L'équipe EcoTravel</p>" +
                    "</body>" +
                "</html>";

        sendEmail(
                "lucasbauduin15@gmail.com",
                email,
                "Vérifiez votre adresse e-mail",
                emailContent
        );
    }
}

