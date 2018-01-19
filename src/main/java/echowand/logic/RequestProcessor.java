package echowand.logic;

import echowand.net.Frame;
import echowand.net.Subnet;

/**
 * リクエストフレームの処理を実行するクラスを作成するためのインタフェースを表す。
 * このインタフェースを継承したクラスを作成して、そのオブジェクトをRequestDispatcherに登録する。
 * @author Yoshiki Makino
 */
public interface RequestProcessor {
    
    /**
     * ESVがSetIであるフレームを受信した場合に呼び出される。
     * フレームが処理済みとする場合にはtrueを返し、そうでなければfalseを返すように実装する。
     * @param subnet 受信したフレームの送受信が行なわれたサブネット
     * @param frame 受信したフレーム
     * @param processed 指定されたフレームがすでに処理済みである場合にはtrue、そうでなければfalse
     * @return フレームが処理済みであればtrue、そうでなければfalse
     */
    public boolean processSetI(Subnet subnet, Frame frame, boolean processed);
    
    /**
     * ESVがSetCであるフレームを受信した場合に呼び出される。
     * フレームが処理済みとする場合にはtrueを返し、そうでなければfalseを返すように実装する。
     * @param subnet 受信したフレームの送受信が行なわれたサブネット
     * @param frame 受信したフレーム
     * @param processed 指定されたフレームがすでに処理済みである場合にはtrue、そうでなければfalse
     * @return フレームが処理済みであればtrue、そうでなければfalse
     */
    public boolean processSetC(Subnet subnet, Frame frame, boolean processed);
    
    /**
     * ESVがGetであるフレームを受信した場合に呼び出される。
     * フレームが処理済みとする場合にはtrueを返し、そうでなければfalseを返すように実装する。
     * @param subnet 受信したフレームの送受信が行なわれたサブネット
     * @param frame 受信したフレーム
     * @param processed 指定されたフレームがすでに処理済みである場合にはtrue、そうでなければfalse
     * @return フレームが処理済みであればtrue、そうでなければfalse
     */
    public boolean processGet(Subnet subnet, Frame frame, boolean processed);
    
    /**
     * ESVがSetGetであるフレームを受信した場合に呼び出される。
     * フレームが処理済みとする場合にはtrueを返し、そうでなければfalseを返すように実装する。
     * @param subnet 受信したフレームの送受信が行なわれたサブネット
     * @param frame 受信したフレーム
     * @param processed 指定されたフレームがすでに処理済みである場合にはtrue、そうでなければfalse
     * @return フレームが処理済みであればtrue、そうでなければfalse
     */
    public boolean processSetGet(Subnet subnet, Frame frame, boolean processed);
    
    /**
     * ESVがINF_REQであるフレームを受信した場合に呼び出される。
     * フレームが処理済みとする場合にはtrueを返し、そうでなければfalseを返すように実装する。
     * @param subnet 受信したフレームの送受信が行なわれたサブネット
     * @param frame 受信したフレーム
     * @param processed 指定されたフレームがすでに処理済みである場合にはtrue、そうでなければfalse
     * @return フレームが処理済みであればtrue、そうでなければfalse
     */
    public boolean processINF_REQ(Subnet subnet, Frame frame, boolean processed);
    
    /**
     * ESVがINFであるフレームを受信した場合に呼び出される。
     * フレームが処理済みとする場合にはtrueを返し、そうでなければfalseを返すように実装する。
     * @param subnet 受信したフレームの送受信が行なわれたサブネット
     * @param frame 受信したフレーム
     * @param processed 指定されたフレームがすでに処理済みである場合にはtrue、そうでなければfalse
     * @return フレームが処理済みであればtrue、そうでなければfalse
     */
    public boolean processINF(Subnet subnet, Frame frame, boolean processed);
    
    /**
     * ESVがINFCであるフレームを受信した場合に呼び出される。
     * フレームが処理済みとする場合にはtrueを返し、そうでなければfalseを返すように実装する。
     * @param subnet 受信したフレームの送受信が行なわれたサブネット
     * @param frame 受信したフレーム
     * @param processed 指定されたフレームがすでに処理済みである場合にはtrue、そうでなければfalse
     * @return フレームが処理済みであればtrue、そうでなければfalse
     */
    public boolean processINFC(Subnet subnet, Frame frame, boolean processed);
}
