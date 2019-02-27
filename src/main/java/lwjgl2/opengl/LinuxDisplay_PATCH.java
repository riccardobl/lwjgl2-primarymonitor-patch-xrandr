package lwjgl2.opengl;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import java.io.ByteArrayInputStream;

public class LinuxDisplay_PATCH implements ClassFileTransformer{
	protected static final ClassPool _CLASS_POOL=ClassPool.getDefault();
	
    @Override
    public byte[] transform(ClassLoader loader, String class_name, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] byte_code) throws IllegalClassFormatException {
        if(class_name==null)return null;
        try{
            if(class_name.equals("org/lwjgl/opengl/LinuxDisplay")){
                System.out.println("Patching "+class_name);
                _CLASS_POOL.importPackage("org.lwjgl.opengl");
                _CLASS_POOL.importPackage("java.lang.reflect");

                ByteArrayInputStream bis=new ByteArrayInputStream(byte_code);
                CtClass ct_class=_CLASS_POOL.makeClass(bis);

                CtMethod getAvailableDisplayModes_method=ct_class.getDeclaredMethod("getAvailableDisplayModes");
                getAvailableDisplayModes_method.insertAt(952,"{\n"+"    "
                    + "Field f = XRandR.class.getDeclaredField(\"primaryScreenIdentifier\");\n"
                    + "f.setAccessible(true);\n"
                    + "resolutions = XRandR.getResolutions((String)f.get(null));\n"
                    + "f.setAccessible(false);\n"
                +"}");
                byte_code=ct_class.toBytecode();
                ct_class.detach();
                bis.close();

                _CLASS_POOL.clearImportedPackages();
                System.out.println("Done");

            }
        }catch(Exception e){
            System.out.println("/!\\ "+class_name+" patching failed :(");
            e.printStackTrace();
            return byte_code;
        }
        return byte_code;
    }


	public static void init(){
		
	}
}