package com.vinh.travelcard.payment.view;

import android.content.Context;
import android.text.*;
import android.text.InputFilter.LengthFilter;
import android.util.AttributeSet;
import com.vinh.travelcard.R;
import com.vinh.travelcard.payment.utils.CardType;

/**
 * An {@link android.widget.EditText} that displays a CVV hint for a given Card type when focused.
 */
public class CvvEditText extends ErrorEditText implements TextWatcher {

    private static final int DEFAULT_MAX_LENGTH = 3;

    private CardType mCardType;

    public CvvEditText(Context context) {
        super(context);
        init();
    }

    public CvvEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CvvEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setInputType(InputType.TYPE_CLASS_NUMBER);
        setFilters(new InputFilter[]{new LengthFilter(DEFAULT_MAX_LENGTH)});
        addTextChangedListener(this);
        setCardIcon(R.drawable.ic_card_security_code);
    }

    /**
     * Sets the card type associated with the security code type. {@link CardType#AMEX} has a
     * different icon and length than other card types. Typically handled through
     * {@link com.braintreepayments.cardform.view.CardEditText.OnCardTypeChangedListener#onCardTypeChanged(com.braintreepayments.cardform.utils.CardType)}.
     *
     * @param cardType Type of card represented by the current value of card number input.
     */
    public void setCardType(CardType cardType) {
        mCardType = cardType;

        InputFilter[] filters = {new LengthFilter(cardType.getSecurityCodeLength())};
        setFilters(filters);

        setContentDescription(getContext().getString(cardType.getSecurityCodeName()));
        //    setFieldHint(cardType.getSecurityCodeName());

        invalidate();
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (mCardType == null) {
            return;
        }

        if (mCardType.getSecurityCodeLength() == editable.length() && getSelectionStart() == editable.length()) {
            validate();

            if (isValid()) {
         //       focusNextView();
            }
        }
    }

    @Override
    public boolean isValid() {
        return isOptional() || getText().toString().length() == getSecurityCodeLength();
    }

    @Override
    public String getErrorMessage() {
        String securityCodeName;
        if (mCardType == null) {
            securityCodeName = getContext().getString(R.string.bt_cvv);
        } else {
            securityCodeName = getContext().getString(mCardType.getSecurityCodeName());
        }

        if (TextUtils.isEmpty(getText())) {
            return getContext().getString(R.string.bt_cvv_required, securityCodeName);
        } else {
            return getContext().getString(R.string.bt_cvv_invalid, securityCodeName);
        }
    }

    private int getSecurityCodeLength() {
        if (mCardType == null) {
            return DEFAULT_MAX_LENGTH;
        } else {
            return mCardType.getSecurityCodeLength();
        }
    }

    private void setCardIcon(int icon) {
        setCompoundDrawablesWithIntrinsicBounds(0, 0, icon, 0);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }
}
