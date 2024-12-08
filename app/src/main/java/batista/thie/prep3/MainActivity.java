package batista.thie.prep3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText numeros;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        numeros = findViewById(R.id.etNum);
        numeros.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        Button mostra = findViewById(R.id.btmostra);
        tvResult = findViewById(R.id.tvResult);
        tvResult.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);


        mostra.setOnClickListener(op -> primos());
    }

    private void primos() {
        try {
            int numero = Integer.parseInt(numeros.getText().toString());

            // Limpa o TextView antes de exibir novos resultados
            tvResult.setText("");

            // Verifica se o número está no intervalo válido
            if (numero >= 0 && numero <= 100) {
                StringBuilder resultados = new StringBuilder("Números primos até ").append(numero).append(": ");
                for (int i = 2; i <= numero; i++) { // Inicia de 2, já que 0 e 1 não são primos
                    if (isPrimo(i)) {
                        resultados.append(i).append(", ");
                    }
                }

                // Remove a última vírgula e exibe o resultado
                if (resultados.length() > 20) { // Verifica se há números primos no intervalo
                    tvResult.setText(resultados.substring(0, resultados.length() - 2));
                } else {
                    tvResult.setText("Nenhum número primo encontrado no intervalo.");
                }
            } else {
                tvResult.setText("Por favor, insira um número entre 0 e 100.");
            }
        } catch (NumberFormatException e) {
            tvResult.setText("Por favor, insira um número válido.");
        }
    }

    public static boolean isPrimo(int num) {
        if (num < 2) {
            return false; // Números menores que 2 não são primos
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false; // Divisível por outro número além de 1 e ele mesmo
            }
        }
        return true;
    }
}



