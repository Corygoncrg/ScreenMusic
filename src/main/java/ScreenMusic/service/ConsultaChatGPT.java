package ScreenMusic.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;

/**
 * Método para consultar o chat GPT para adquirir informações sobre um artista
 */
public class ConsultaChatGPT {

        public static String buscarArtista(String texto) {
            OpenAiService service = new OpenAiService("ChatGPT_KEY");

            CompletionRequest requisicao = CompletionRequest.builder()
                    .prompt("Me fale sobre o artista: " + texto)
                    .model("gpt-3.5-turbo-instruct")
                    .maxTokens(300)
                    .build();

            CompletionResult resposta = service.createCompletion(requisicao);
            return resposta.getChoices().get(0).getText();
        }

    }

