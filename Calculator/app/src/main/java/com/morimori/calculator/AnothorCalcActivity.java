package com.morimori.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.text.Editable;
import android.widget.Spinner;
import android.widget.TextView;


public class AnothorCalcActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener
{
	private EditText numberInput1;
	private EditText numberInput2;
	private Spinner operatorSelector;
	private TextView calcResult;

	private static final int REQUEST_CODE_ANOTHOR_CALC_1 = 1;
	private static final int REQUEST_CODE_ANOTHOR_CALC_2 = 2;

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

		findViewById(R.id.backButton).setOnClickListener(this);
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
	public void onClick(View v)
	{
		if (!checkEditTextInput())
		{
			setResult(RESULT_CANCELED);
		}
		else
		{
			int result = calc();

			Intent data = new Intent();
			data.putExtra("result", result);
			setResult(RESULT_OK, data);
		}
		finish();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode != RESULT_OK) return;

		Bundle resultBundle = data.getExtras();

		if (!resultBundle.containsKey("result")) return;

		int result = resultBundle.getInt("result");

		if (resultCode == REQUEST_CODE_ANOTHOR_CALC_1)
		{
			numberInput1.setText(String.valueOf(result));
		}
		else if (resultCode == REQUEST_CODE_ANOTHOR_CALC_2)
		{
			numberInput2.setText(String.valueOf(result));
		}
		refreshResult();
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
