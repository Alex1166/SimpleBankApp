package my.bankapp;

public class CommandHelper {

    private static void doShowHelp() {
        System.out.println("""
                available commands:\s
                 whoami - show current user\s
                 login - authorize in system\s
                 logoff - logoff to switch user\s
                 reg - create new user\s
                 chpwd - change your password\s
                 newacc - create new account\s
                 myacc - show your account info\s
                 put - put money on your account\s
                 send - transfer money to another user\s
                 withdraw - get money from your account""");
    }

    public static boolean processCommand(Session session, String command) {
        return switch (command) {
            case "start":
                yield true;
            case "exit":
                yield false;
            case "help":
                doShowHelp();
                yield true;
            case "whoami":
                if (session.getUser() != null) {
                    System.out.println("You are " + session.getUser().getLogin());
                } else {
                    System.out.println("You are not authorized");
                }
                yield true;
            case "login":
                UserHelper.doLogin(session);
                yield true;
            case "logoff":
                UserHelper.doLogoff(session);
                yield true;
            case "reg":
                UserHelper.doRegister(session);
                yield true;
            case "chpwd":
                UserHelper.doChangePassword(session.getUser());
                yield true;
            case "newacc":
                AccountHelper.doCreateAccount(session.getUser());
                yield true;
            case "myacc":
                AccountHelper.doGetAccountInfo(session.getUser());
                yield true;
            case "put":
                MoneyHelper.doPutMoney(session.getUser());
                yield true;
            case "send":
                MoneyHelper.doSendMoney(session.getUser());
                yield true;
            case "withdraw":
                MoneyHelper.doGetMoney(session.getUser());
                yield true;
            default:
                System.out.println("Unknown command");
                yield true;
        };
    }
}
