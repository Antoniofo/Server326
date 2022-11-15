import ctrl.Ctrl;
import ihm.Ihm;
import wrk.Wrk;

public class Main {
    public static void main(String[] args) {
        Ctrl ctrl = new Ctrl();
        Ihm ihm = new Ihm();
        Wrk wrk = new Wrk();

        wrk.setRefCtrl(ctrl);
        ihm.setRefCtrl(ctrl);
        ctrl.setRefIhm(ihm);
        ctrl.setRefWrk(wrk);

        ctrl.start();
    }
}