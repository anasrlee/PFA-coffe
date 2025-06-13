package com.coffee.machine.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime generatedAt;
    private String content;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public static Receipt generateReceipt(Payment payment) {
        Receipt receipt = new Receipt();
        receipt.setGeneratedAt(LocalDateTime.now());
        receipt.setPayment(payment);

        // Génération du contenu du reçu
        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append("=== Reçu de paiement ===\n")
                .append("Date: ").append(receipt.getGeneratedAt()).append("\n")
                .append("Commande #").append(payment.getOrder().getId()).append("\n")
                .append("Montant: ").append(payment.getAmount()).append("\n")
                .append("Méthode: ").append(payment.getMethod()).append("\n")
                .append("Merci pour votre visite!");

        receipt.setContent(contentBuilder.toString());
        return receipt;
    }
}
