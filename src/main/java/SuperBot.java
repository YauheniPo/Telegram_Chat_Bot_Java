import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SuperBot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new SuperBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setParseMode("HTML");
        sendMessage.setChatId(message.getChatId());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

//        sendInlineKeyBoardMessage(sendMessage);
        setButtons(sendMessage);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String text;
        if (message != null) {
            text = message.getText();
            System.out.println(text);
            if (message.hasText()){
                switch (text) {
                    case "/start":
                        sendMsg(message,
                                "Привет, рады вас видеть в нашем дружном профориентированном чате!" +
                                "\n" +
                                "Доступная информация в чате:\n" +
                                "/about - О нас.\n" +
                                "/education - Список образовательных учреждений в сфере IT.\n" +
                                "/professions - Список профессий в сфере IT.\n" +
                                "/info - Важная информация.\n");
                        break;
                    case "/about":
                        sendMsg(message,
                                "Наш чат будет полезен всем, кто желает связать свою профессиональную деятельность" +
                                " с информационными технологиями. В чате мы общаемся на профориентированные темы, задаем" +
                                " интересующие вопросы и делимся своим опытом с другими и находим новых друзей и интересную работу" +
                                "\n" +
                                "- ссылка на наш сайт - https://alisapushnova.wixsite.com/invo-profic\n" +
                                "- ссылка на тест по профориентации - https://docs.google.com/forms/d/e/1FAIpQLSeqjK-YspD9dAjV8JVnOZGXyqs3Hc_A70FSrgZBy3v1FfE0Bg/viewform?vc=0&c=0&w=1");
                        //Тут пропиши путь к фотографии
                        sendSticker(message, "...\\photo.jpg");
                        break;
                    case "/education":
                        sendMsg(message,
                                "БНТУ - http://www.bntu.by/\n" +
                                "БГУ - https://www.bsu.by/\n" +
                                "БГУИР - https://www.bsuir.by/\n" +
                                "Филиал БГУИР 'Минский радиотехнический колледж - http://www.mrk-bsuir.by/ru/\n" +
                                "УО 'МИНСКИЙ ГОСУДАРСТВЕННЫЙ КОЛЛЕДЖ ЭЛЕКТРОНИКИ' - http://mgke.minsk.edu.by/");
                        break;
                    case "/professions":
                        sendMsg(message, "<b>Важная информация!!!</b>\n" +
                                "Перечень противопоказаний МРЭК в сфере техники и технологии:\n" +
                                "1. Состояние после радикального лечения злокачественных новообразований - вопрос решается индивидуально по заключению врача-онколога.\n" +
                                "2. Злокачественные новообразования лимфоидной, кроветворной и родственных им тканей в стадии ремиссии - вопрос решается индивидуально по заключению врача-онколога и/или врача-гематолога.\n" +
                                "3. Нарушения свертываемости крови.\n" +
                                "4. Стойкие посттравматические, дегенеративные, воспалительные изменения крупных суставов с выраженными ограничениями движений.\n" +
                                "5. Отсутствие двух нижних конечностей.\n" +
                                "6. Отсутствие одной или двух верхних конечностей.\n" +
                                "7. Полное нарушение охватывающей и удерживающей функций кисти ведущей руки.\n" +
                                "8. Заболевания центральной и периферической нервной системы при наличии стойких выраженных нарушений движений, чувствительности или трофики.\n" +
                                "9. Эпилепсия, за исключением случаев эпилепсии с редкими генерализованными (судорожными и бессудорожными), парциальными, неклассифицированными припадками без интеллектуально-мнестических нарушений и выраженных изменений личности, при наличии критического отношения к заболеванию и сохраненной трудоспособности.\n" +
                                "10. Восприятие шепотной речи на оба уха на расстоянии 1 м и ближе, разговорной - не менее 2 м, развитие речи не ниже 3-го уровня - не противопоказано при условии последующего трудоустройства вне контакта с повышенными уровнями производственного шума и вибрации.\n" +
                                "11. Снижение остроты зрения с коррекцией ниже 0,5 на лучше видящем глазу и ниже 0,2 на хуже видящем глазу.\n" +
                                "12. Осложненная хориоретинальная миопия независимо от степени (при хронической периферической дистрофии сетчатки - с наличием предразрыва или полного ее разрыва).\n" +
                                "13. Гиперметропия свыше 6,0 D, астигматизм любого вида свыше 3,0 D.\n" +
                                "14. Отсутствие бинокулярного зрения.\n" +
                                "15. Аномалии цветового зрения - противопоказано трудоустройство на должности, связанные с различением цветов и цветовых сигналов.\n" +
                                "16. Хронические рецидивирующие и прогрессирующие болезни сосудистой оболочки, сетчатки, зрительного нерва и зрительных путей - вопрос решается индивидуально по заключению врача-офтальмолога.\n" +
                                "17. Глаукома.\n" +
                                "\n" +
                                "Профессии:\n" +
                                "1. Мобильный разработчик\n" +
                                "2. UX-дизайнер\n" +
                                "3. Разработчик backend\n" +
                                "4. QA инженер");
                        break;
                    case "/info":
                        sendMsg(message,
                                "Важные ссылки:\n" +
                                        "- http://www.otkrovenie.by/\n" +
                                        "- http://mrek.by/\n");
                        break;
                    case "/test":
                        sendMsg(message,
                                "https://docs.google.com/forms/d/e/1FAIpQLSeqjK-YspD9dAjV8JVnOZGXyqs3Hc_A70FSrgZBy3v1FfE0Bg/viewform?vc=0&c=0&w=1");
                        break;
                    case "/site":
                        sendMsg(message,
                                "https://alisapushnova.wixsite.com/invo-profic");
                        break;
                    default:
                        break;
                }
            }
        } else if(update.hasCallbackQuery()){
            sendCallbackQueryMsg(update);
        }
    }

    private synchronized  void sendCallbackQueryMsg(Update update) {
        try {
            execute(new SendMessage()
                    .setText(update.getCallbackQuery().getData())
                    .setChatId(update.getCallbackQuery().getMessage().getChatId()));
            if (update.getCallbackQuery().getData().equals("/test")) {
                execute(new SendMessage()
                        .setText("https://docs.google.com/forms/d/e/1FAIpQLSeqjK-YspD9dAjV8JVnOZGXyqs3Hc_A70FSrgZBy3v1FfE0Bg/viewform?vc=0&c=0&w=1")
                        .setChatId(update.getCallbackQuery().getMessage().getChatId()));
            } else if (update.getCallbackQuery().getData().equals("/site")) {
                execute(new SendMessage()
                        .setText("https://alisapushnova.wixsite.com/invo-profic")
                        .setChatId(update.getCallbackQuery().getMessage().getChatId()));
            } else {
                execute(new SendMessage()
                        .setText(update.getCallbackQuery().getData())
                        .setChatId(update.getCallbackQuery().getMessage().getChatId()));
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendSticker(Message message, String stickerPath) {
        SendSticker sticker = new SendSticker();
        sticker.setChatId(message.getChatId());
        File stickerFile = new File(stickerPath);
        sticker.setSticker(stickerFile);
        try {
            execute(sticker);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

//    private synchronized void sendInlineKeyBoardMessage(SendMessage sendMessage) {
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
//        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
//        inlineKeyboardButton1.setText("Тест по профориентации");
//        inlineKeyboardButton1.setCallbackData("/test");
//        inlineKeyboardButton2.setText("Наш сайт");
//        inlineKeyboardButton2.setCallbackData("/site");
//        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
//        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
//        keyboardButtonsRow1.add(inlineKeyboardButton1);
//        keyboardButtonsRow2.add(inlineKeyboardButton2);
//        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
//        rowList.add(keyboardButtonsRow1);
//        rowList.add(keyboardButtonsRow2);
//        inlineKeyboardMarkup.setKeyboard(rowList);
//        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
//    }

    public synchronized void setButtons(SendMessage sendMessage) {
        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("/start"));

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("/about"));
        keyboardSecondRow.add(new KeyboardButton("/education"));
        keyboardSecondRow.add(new KeyboardButton("/professions"));
        keyboardSecondRow.add(new KeyboardButton("/info"));

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);

        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    public String getBotUsername() {
        //Имя бота в Telegram
        return "";
    }

    public String getBotToken() {
        //Тут должен быть токен, который получала у BotFather Юля, когда создавала бота
        return "";
    }
}
