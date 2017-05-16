package com.morimori.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.text.Editable;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity implements TextWatcher
{
	private EditText numberInput1;
	private EditText numberInput2;
	private Spinner operatorSelector;
	private TextView calcResult;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		numberInput1 = (EditText)findViewById(R.id.numberInput1);
		numberInput1.addTextChangedListener(this);

		numberInput2 = (EditText)findViewById(R.id.numberInput2);
		numberInput2.addTextChangedListener(this);

		operatorSelector = (Spinner)findViewById(R.id.operatorSelector);
		calcResult = (TextView)findViewById(R.id.calcResult);
	}

	private boolean checkEditTextInput()
	{
		String input1 = numberInput1.getText().toString();
		String input2 = numberInput2.getText().toString();

		return !TextUtils.isEmpty(input1) && !TextUtils.isEmpty(input2);
	}


	private void refreshResult()
	{
		if (checkEditTextInput())
		{
			int result = calc();

			String resultText = getString(R.string.calc_result_text, result);
			calcResult.setText(resultText);
		}
		else
		{
			calcResult.setText(R.string.calc_result_default);
		}
	}

	private int calc()
	{
		String input1 = numberInput1.getText().toString();
		String input2 = numberInput2.getText().toString();

		int number1 = Integer.parseInt(input1);
		int number2 = Integer.parseInt(input2);

		int operator = operatorSelector.getSelectedItemPosition();

		switch (operator)
		{
			case 0:
				return number1 + number2;

			case 1:
				return number1 - number2;

			case 2:
				return number1 * number2;

			case 3:
				return number1 / number2;

			default:
				throw new RuntimeException();

		}

	}

	@Override
	public void afterTextChanged(Editable s)
	{
		refreshResult();
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after)
	{

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count)
	{

	}
}
