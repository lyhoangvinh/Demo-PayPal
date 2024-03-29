package com.vinh.travelcard.payment;

/**
 * Listener to receive a callback when the card form becomes valid or invalid
 */
public interface OnCardFormValidListener {

    /**
     * Called when the card form becomes valid or invalid
     * @param valid indicates wither the card form is currently valid or invalid
     */
    void onCardFormValid(boolean valid);
}
