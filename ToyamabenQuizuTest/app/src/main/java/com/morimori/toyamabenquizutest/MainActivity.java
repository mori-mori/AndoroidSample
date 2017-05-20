package com.morimori.toyamabenquizutest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import android.content.Context;
import android.content.res.AssetManager;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends Activity implements View.OnClickListener
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		((Button)findViewById(R.id.startButton)).setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		CSVParser cs = new CSVParser(this, "test.csv");
		cs.parse();
	}


	public class CSVParser
	{
		private Context context;
		private String file;

		public CSVParser(Context context, String file){
			this.file = file;
			this.context = context;
		}

		public void parse() {
			// AssetManagerの呼び出し
			AssetManager assetManager = context.getResources().getAssets();
			try {
				// CSVファイルの読み込み
				InputStream is = assetManager.open(file);
				InputStreamReader inputStreamReader = new InputStreamReader(is);
				BufferedReader bufferReader = new BufferedReader(inputStreamReader);
				String line = "";
				String txt = "";

				while ((line = bufferReader.readLine()) != null) {

					// 各行が","で区切られていて5つの項目
					StringTokenizer st = new StringTokenizer(line, ",");
					String first = st.nextToken();
					String second = st.nextToken();
					String third = st.nextToken();
					String fourth = st.nextToken();
					String fifth = st.nextToken();

					txt = first + " " + second + " " + third + " " + fourth + " " + fifth + "\n";

					//文字列を追加する
					((TextView)findViewById(R.id.contentsTextView)).append(txt);
				}

				bufferReader.close();

			} catch (IOException e) {
				//e.printStackTrace();
				((TextView)findViewById(R.id.contentsTextView)).setText("読み込みに失敗しました・・・");
			}
		}
	}
}
