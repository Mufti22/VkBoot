package ru.vkbot;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.*;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;
public class Bot {
    public static void main(String[] args) throws ClientException, ApiException, InterruptedException
    {
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        Random random = new Random();
        Keyboard keyboard = new Keyboard();

//        List<List<KeyboardButton>> allKey = new ArrayList<>();
//        List<KeyboardButton> line1 = new ArrayList<>();
//        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("❤").setType(KeyboardButtonActionType.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
//        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Кто я?").setType(KeyboardButtonActionType.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
//        allKey.add(line1);
//        keyboard.setButtons(allKey);

        GroupActor actor = new GroupActor();
        Integer ts = vk.messages().getLongPollServer(actor).execute().getTs();
        while (true){
            MessagesGetLongPollHistoryQuery historyQuery =  vk.messages().getLongPollHistory(actor).ts(ts);
            List<Message> messages = historyQuery.execute().getMessages().getItems();
            if (!messages.isEmpty()){
                messages.forEach(message -> {
                    System.out.println(message.toString());
                    try {
                        if (message.getText().equals("Привет")){
                            vk.messages().send(actor).message("Привет! Это-чат бот АВТФ! Хочешь узнать, что смогу? пиши : help ").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                        else if (message.getText().equals("help")) {
                            vk.messages().send(actor).message("Пишите пункт:\n1)Расписание\n2)Информация о активе\n3)Проблемы с учебой\n4)Кто меня создал").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                        else if (message.getText().equals("1")) {
                            vk.messages().send(actor).message("На сайте отсуствует информация:(").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }

                        else if (message.getText().equals("2")) {
                            vk.messages().send(actor).message("Староста:Ирина Попова @irppv16\n как попасть в актив? бля хуй знает Лиза и Геля придумайте че писать").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                        else if (message.getText().equals("3")) {
                            vk.messages().send(actor).message("Какой предмет хотите? просто пишите").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                        else if (message.getText().equals("4")) {
                            vk.messages().send(actor).message("Малик Бахаров: @tusbozuk").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                        else if (message.getText().equals("Тервер")) {
                            vk.messages().send(actor).message("Поможет Юлия Голекбарова: @kuronaa").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }

//                        else if (message.getText().equals("Кнопки")) {
//                            vk.messages().send(actor).message("А вот и они").userId(message.getFromId()).randomId(random.nextInt(10000)).keyboard(keyboard).execute();
//                        }
                        else {
                            vk.messages().send(actor).message("Ошибка!").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                    }
                    catch (ApiException | ClientException e) {e.printStackTrace();}
                });
            }
            ts = vk.messages().getLongPollServer(actor).execute().getTs();
            Thread.sleep(500);
        }
    }
}

