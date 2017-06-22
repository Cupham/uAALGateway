package mainpackage;

/**
 * Created by Roberto on 15/05/2017.
 */
public class UAALinterface {

    public static String sendMessage(String message){

        String reply;

        // : Parse the message
        String[] msg = message.split("#");
        String msg_command = msg[0];
        String msg_type_ID = msg[1];
        String msg_content = "";
        if (msg.length == 3){
            msg_content = msg[2];
        }

        // : Call the required function
        switch (msg_command){
            case "publish":
                reply = Activator.cpublisher.publishContextEvent(msg_type_ID, msg_content);
                break;
            case "request":
                reply = Activator.scaller.requestMessage(msg_type_ID);
                break;
            case "get":

                reply = "gotten#" + msg_type_ID + "#";

                if (Component.is_Cahrim){
                    switch (msg_type_ID){
                        case "D7.1":
                            reply = reply + Activator.i_D7_1.getMessage();
                            break;
                        case "D7.2":
                            reply = reply + Activator.i_D7_2.getMessage();
                            break;
                        case "D11.1":
                            reply = reply + Activator.i_D11_1.getMessage();
                            break;
                        case "D11.2":
                            reply = reply + Activator.i_D11_2.getMessage();
                            break;
                        case "D11.3":
                            reply = reply + Activator.i_D11_3.getMessage();
                            break;
                        case "D11.4":
                            reply = reply + Activator.i_D11_4.getMessage();
                            break;
                        default:
                            reply = "INFO: No message found";}
                }
                if (Component.is_Ckb){
                    switch (msg_type_ID){
                        case "D5.1":
                            reply = reply + Activator.i_D5_1.getMessage();
                            break;
                        case "D5.2":
                            reply = reply + Activator.i_D5_2.getMessage();
                            break;
                        case "D8.1":
                            reply = reply + Activator.i_D8_1.getMessage();
                            break;
                        case "D8.2":
                            reply = reply + Activator.i_D8_2.getMessage();
                            break;
                        default:
                            reply = "INFO: No message found";
                    }
                }
                if (Component.is_Cspem){
                    switch (msg_type_ID){
                        case "D1.1":
                            reply = reply + Activator.i_D1_1.getMessage();
                            break;
                        case "D1.2":
                            reply = reply + Activator.i_D1_2.getMessage();
                            break;
                        case "D2.1":
                            reply = reply + Activator.i_D2_1.getMessage();
                            break;
                        case "D2.2":
                            reply = reply + Activator.i_D2_2.getMessage();
                            break;
                        case "D2.3":
                            reply = reply + Activator.i_D2_3.getMessage();
                            break;
                        case "D2.4":
                            reply = reply + Activator.i_D2_4.getMessage();
                            break;
                        case "D3.1":
                            reply = reply + Activator.i_D3_1.getMessage();
                            break;
                        case "D3.2":
                            reply = reply + Activator.i_D3_2.getMessage();
                            break;
                        case "D4.1":
                            reply = reply + Activator.i_D4_1.getMessage();
                            break;
                        case "D4.2":
                            reply = reply + Activator.i_D4_2.getMessage();
                            break;
                        case "D5.1":
                            reply = reply + Activator.i_D5_1.getMessage();
                            break;
                        case "D5.2":
                            reply = reply + Activator.i_D5_2.getMessage();
                            break;
                        case "D6.1":
                            reply = reply + Activator.i_D6_1.getMessage();
                            break;
                        case "D6.2":
                            reply = reply + Activator.i_D6_2.getMessage();
                            break;
                        case "D6.3":
                            reply = reply + Activator.i_D6_3.getMessage();
                            break;
                        default:
                            reply = "INFO: No message found";
                    }
                }
                break;

            default:
                reply = "ERROR: Invalid message";


        }

        return reply;
    }
}
