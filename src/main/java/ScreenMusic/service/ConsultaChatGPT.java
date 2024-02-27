package ScreenMusic.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;


public class ConsultaChatGPT {

        public static String buscarArtista(String texto) {
            OpenAiService service = new OpenAiService("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");

            CompletionRequest requisicao = CompletionRequest.builder()
                    .prompt("Me diga quem é : " + texto)
                    .model("babbage-002")
                    .maxTokens(100)
                    .build();

            CompletionResult resposta = service.createCompletion(requisicao);
            return resposta.getChoices().get(0).getText();
        }

    }

