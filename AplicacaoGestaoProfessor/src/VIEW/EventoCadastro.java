package VIEW;

import java.util.ArrayList;
import java.util.List;

public class EventoCadastro {

    private static final List<OnDadosAlteradosListener> listeners = new ArrayList<>();

    public static void registrar(OnDadosAlteradosListener l) {
        if (!listeners.contains(l)) listeners.add(l);
    }

    public static void remover(OnDadosAlteradosListener l) {
        listeners.remove(l);
    }

    public static void notificar() {
        for (OnDadosAlteradosListener l : new ArrayList<>(listeners)) {
            l.ListarDadosAoAlterar();
        }
    }
}