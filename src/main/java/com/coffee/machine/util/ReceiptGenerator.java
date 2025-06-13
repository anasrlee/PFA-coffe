package com.coffee.machine.util;

import com.coffee.machine.model.Order;
import com.coffee.machine.model.Payment;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class ReceiptGenerator {

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public String generateReceiptContent(Payment payment) {
        Order order = payment.getOrder();

        StringBuilder sb = new StringBuilder();
        sb.append("=== CAFÉ LITTÉRAIRE ===\n")
                .append("Reçu #").append(payment.getId()).append("\n")
                .append("Date: ").append(payment.getPaymentTime().format(DATE_FORMAT)).append("\n\n")
                .append("Commande #").append(order.getId()).append("\n")
                .append("Serveur: ").append(order.getServer().getUsername()).append("\n")
                .append("Table: ").append(order.getTable().getTableNumber()).append("\n\n")
                .append("Articles:\n");

        order.getItems().forEach(item ->
                sb.append("- ").append(item.getQuantity())
                        .append(" x ").append(item.getIngredient().getName())
                        .append(" (").append(item.getSpecialInstruction()).append(")\n"));

        sb.append("\nTotal: ").append(payment.getAmount()).append(" €\n")
                .append("Méthode: ").append(payment.getMethod()).append("\n\n")
                .append("Merci pour votre visite !\n")
                .append("=== FIN DE RECU ===");

        return sb.toString();
    }
}
