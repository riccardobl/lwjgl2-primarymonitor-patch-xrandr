package lwjgl2;
import java.lang.instrument.Instrumentation;


public class PrimaryScreenPatch{

	public static void premain(String agentArguments, Instrumentation instrumentation) {
		instrumentation.addTransformer(new lwjgl2.opengl.LinuxDisplay_PATCH());
	}

}