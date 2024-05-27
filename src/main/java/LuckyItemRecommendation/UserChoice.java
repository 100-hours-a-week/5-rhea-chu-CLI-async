package LuckyItemRecommendation;

import LuckyItemRecommendation.ItemList.LuckyItem;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UserChoice implements LuckyItemQuestion {
    Scanner sc = new Scanner(System.in);

    @Override
    public void TodayWeather() {

            String[] weather = {"1. 맑음", "2. 흐림", "3. 비", "4. 더움", "5. 추움"};
            String weatherMsg = "오 늘 의 날 씨 는?";
            MainMsg(weatherMsg);
            UserInputHandler(weather, weatherMsg, 5);

            TodayFeelings();
    }

    @Override
    public void TodayFeelings() {
            String[] Feelings = {"1. 상 쾌", "2. 우 울", "3. 분 노", "4. 유 쾌",
                    "5. 평 범", "6. 짜 증", "7. 따 분", "8. 짜 릿"};
            String FeelingsMsg = "오 늘 의 기 분 은 ?";
            UserInputHandler(Feelings,FeelingsMsg, 8 );

            AloneOrNot();
    }

    @Override
    public void AloneOrNot() {
        String[] alone  = {"1. 네", "2. 아니오"};
        String aloneMsg = "혼 자 이 신 가 요 ?";
        int choice = UserInputHandler(alone, aloneMsg, 2);
        if(choice == 1){
            GoingWhere();
        }
        else{
            MeetWho who = new MeetWho();
            who.MeetWho();
        }
    }

    public void GoingWhere() {
        String[] where = {"1. 카 페", "2. 도서관", "3. 학 교", "4. 회 사",
                    "5. 놀이 공원", "6. 바 다", "7. 숲 & 산", "8. 전 시 회", "9. 집이 최고"};
        String whereMsg = "어 디 로 가 볼 건 가 요?";
        UserInputHandler(where, whereMsg, 9);
        GoingHow goingHow = new GoingHow();
        goingHow.GoHow();
    }

    @Override
    public void LuckyPrint() {
        String last;
        boolean repeat;

        do {
            System.out.println(".･ ｡ ⊹ + ஐ 행 운 을 불 러 오 는 중 ஐ + ⊹ ｡ ･." + "\n");
            Thread sleepThread = new Thread(() -> {
                try{
                    Thread.sleep(3000);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            sleepThread.start();
            try {
                sleepThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            LuckyItem item = new LuckyItem();
            LuckyListPrint(item, "아 이 템 은 ?", "LuckyThing");
            LuckyListPrint(item, "컬 러 는 ?", "LuckyColor");
            LuckyListPrint(item, "숫 자 는 ?", "LuckyNumber");

            System.out.println(" .･ ｡ ⊹ + ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ + ⊹ ｡ ･.");
            System.out.println(" .･ ｡ ⊹ + ஐ 추 천 템 을 활 용 하 시 고 ஐ + ⊹ ｡ ･.");
            System.out.println(".･ ｡ ⊹ + ஐ ஐ ஐ ஐ 오 늘 하 루 ஐ ஐ ஐ ஐ + ⊹ ｡ ･.");
            System.out.println(".･ ｡ ⊹ + ஐ ஐ 행 운 이 가 득 하 기 를 ஐ ஐ + ⊹ ｡ ･.");
            System.out.println(" .･ ｡ ⊹ + ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ + ⊹ ｡ ･." + "\n");

            System.out.println(" .･ ｡ ⊹ + ஐ 다 시 하 시 겠 습 니 까 ? ஐ + ⊹ ｡ ･.");
            System.out.println("  .･ ｡ ⊹ + ஐ ஐ ஐ ஐ y / n ஐ ஐ ஐ ஐ + ⊹ ｡ ･."+ "\n");

            last  = sc.next();
            switch (last){
                case "y" -> {
                    TodayWeather();
                    repeat = true;
                }
                case "n" -> {
                    System.out.println(".･ ｡ ⊹ + ஐ ஐ 행 운 을 빕 니 다 ஐ ஐ *+ ⊹ ｡ ･.");
                    System.out.println(".･ ｡ ⊹ + ஐ ஐ T H E - E N D ஐ ஐ *+ ⊹ ｡ ･.");
                    System.exit(0);
                    repeat = false;
                }
                default -> {
                    ErrorMsg();
                    repeat = true;
                }
            }
        }
        while(repeat);
    }
    @Override
    public int UserInputHandler(String[] options, String message, int maxChoice){
        boolean isValid;
        int choice = -1;
        do{
            MainMsg(message);
            PrintOptions(options);
            ChoiceMsg();
            try{
                choice = sc.nextInt();
                isValid = (choice >= 1 && choice <= maxChoice);
                if(!isValid){
                    ErrorMsg();
                }
            }
            catch (InputMismatchException e){
                ErrorMsg();
                sc.nextLine(); // 커치안에서 무한 반복 탈출 위해서 재 ���기화
                isValid = false;
            }
        }
        while(!isValid);// 유효하지 않은 경우 반복
        return choice;
    }

    // 문항 출력
    public void PrintOptions(String[] options){
        for(String s : options){
            System.out.println(".･ ｡ ⊹ + ஐ ஐ ஐ ஐ " + s + " ஐ ஐ ஐ ஐ + ⊹ ｡ ･.");
        }
    }

    // 입력 오류 메세지
    public void ErrorMsg(){
        System.out.println(".･ ｡ ⊹ + ஐ 잘 못 된 입 력 입 니 다 ! ஐ + ⊹ ｡ ･.");
        System.out.println(".･ ｡ ⊹ + ஐ 다 시 입 력 헤 주 세 요 ! ஐ + ⊹ ｡ ･."+ "\n");
    }

    // 숫자 입력 메세지
    public void ChoiceMsg(){
        System.out.println(".･ ｡ ⊹ + ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ + ⊹ ｡ ･." + "\n");
        System.out.println(".･ ｡ ⊹ + ஐ ஐ 당 신 의 선 택 은 ? ஐ ஐ + ⊹ ｡ ･.");
    }

    // 메인 메세지
    public void MainMsg(String msg){
        System.out.println(".･ ｡ ⊹ + ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ + ⊹ ｡ ･.");
        System.out.println(".･ ｡ ⊹ + ஐ ஐ " + msg +" ஐ ஐ + ⊹ ｡ ･.");
        System.out.println(".･ ｡ ⊹ + ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ ஐ + ⊹ ｡ ･.");
    }

    // 행운 목록 출력 메세지
    private void LuckyListPrint(LuckyItem item, String list, String methodName){
        System.out.println(".･ ｡ ⊹ + ஐ 당 신 의 행 운 을 높 여 줄 ஐ + ⊹ ｡ ･.");
        System.out.println(".･ ｡ ⊹ + ஐ ஐ ஐ "+ list +" ஐ ஐ ஐ + ⊹ ｡ ･." + "\n");
        try{
            item.getClass().getMethod(methodName).invoke(item);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
