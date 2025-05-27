package com.javafullstackEcommerce.ecommerce.modal;

import com.javafullstackEcommerce.ecommerce.domain.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {

    private String paymentId;

    private String razorpayPaymentLinkId;

    private String razorpayPaymentLinkReferenceId;

    private String razorpayPaymentLinksStatus;

    private String razorpayPaymentIdZWSP;

    private PaymentStatus status;




}
