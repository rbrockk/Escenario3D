import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import javax.swing.JFrame;
import java.io.File;
import java.io.IOException;

public class CuboJOGL implements GLEventListener {
    private float rotationAngle = 0.0f;
    private GL2 gl; 
    private Texture chickenTexture;
    private Texture wingsTexture;
    private Texture headTexture;
    private Texture crestTexture;
    private Texture beakTexture;
    private Texture legsTexture;
    private Texture clawsTexture;
    private Texture eyesTexture;

    public static void main(String[] args) {
        GLProfile glProfile = GLProfile.getDefault();
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        GLCanvas glCanvas = new GLCanvas(glCapabilities);

        CuboJOGL cubeScenario = new CuboJOGL();
        glCanvas.addGLEventListener(cubeScenario);

        JFrame frame = new JFrame("Escenario de Cubo con JOGL");
        frame.getContentPane().add(glCanvas);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        FPSAnimator animator = new FPSAnimator(glCanvas, 60, true);
        animator.start();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        gl = drawable.getGL().getGL2();
        gl.glClearColor(0.666f, 0.333f, 0.999f, 1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);

        // Cargar la textura
        try {
            File headImage = new File("imagenes/cuerpo.jpg"); 
            File chickenImage = new File("imagenes/cuerpo.jpg");  
            File wingsImage = new File("imagenes/cuerpo.jpg");  
            File crestImage = new File("imagenes/cresta.png");
            File beakImage = new File("imagenes/pico.png");
            File legsImage = new File("imagenes/patas-garras.jpg");
            File clawsImage = new File("imagenes/patas-garras.jpg");
            File eyesImage = new File("imagenes/black.jpg");
            wingsTexture = TextureIO.newTexture(wingsImage, true);
            chickenTexture = TextureIO.newTexture(chickenImage, true);
            headTexture = TextureIO.newTexture(headImage, true);
            crestTexture = TextureIO.newTexture(crestImage, true);
            beakTexture = TextureIO.newTexture(beakImage, true);
            legsTexture = TextureIO.newTexture(legsImage, true);
            clawsTexture = TextureIO.newTexture(clawsImage, true);
            eyesTexture = TextureIO.newTexture(eyesImage, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
        gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_CLAMP);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_CLAMP);
        
        eyesTexture.bind(gl);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        // Rotate el cubo
        gl.glTranslatef(0.0f, 0.0f, -5.0f);
        gl.glRotatef(rotationAngle, 0.0f, 1.0f, 0.0f);


        drawChicken();

        rotationAngle += 1.0f;
    }

    void drawChicken() {
        chickenTexture.bind(gl);
        drawBody();
        wingsTexture.bind(gl);
        drawWings();
        headTexture.bind(gl);
        drawHead();
        crestTexture.bind(gl);
        drawCrest();
        beakTexture.bind(gl);
        drawBeak();
        legsTexture.bind(gl);
        drawLegs();
        clawsTexture.bind(gl);
        drawClaws();
        eyesTexture.bind(gl);
        drawEyes();
        
    }

    void drawBody() {
    gl.glBegin(GL2.GL_QUADS);
    // Cuerpo del pollo

    // Cara inferior
    gl.glColor3f(1.0f, 1.0f, 1.0f);  // Blanco
    gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(-0.5f, -0.5f, -0.5f);
    gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(0.5f, -0.5f, -0.5f);
    gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(0.5f, -0.5f, 0.7f);
    gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(-0.5f, -0.5f, 0.7f);

    // Cara Superior
    gl.glColor3f(1.0f, 1.0f, 1.0f);  // Blanco
    gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(0.5f, 0.5f, 0.7f);
    gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(-0.5f, 0.5f, 0.7f);
    gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(-0.5f, 0.5f, -0.5f);
    gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(0.5f, 0.5f, -0.5f);

    // Cara derecha
    gl.glColor3f(1.0f, 1.0f, 1.0f);  // Blanco
    gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(0.5f, -0.5f, -0.5f);
    gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(0.5f, 0.5f, -0.5f);
    gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(0.5f, 0.5f, 0.7f);
    gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(0.5f, -0.5f, 0.7f);

    // Cara Izquierda
    gl.glColor3f(1.0f, 1.0f, 1.0f);  // Blanco
    gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(-0.5f, 0.5f, 0.7f);
    gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(-0.5f, -0.5f, 0.7f);
    gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(-0.5f, -0.5f, -0.5f);
    gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(-0.5f, 0.5f, -0.5f);

    // Cara frontal
    gl.glColor3f(1.0f, 1.0f, 1.0f);  // Blanco
    gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(-0.5f, -0.5f, 0.7f);
    gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(0.5f, -0.5f, 0.7f);
    gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(0.5f, 0.5f, 0.7f);
    gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(-0.5f, 0.5f, 0.7f);

    // Cara Trasera
    gl.glColor3f(1.0f, 1.0f, 1.0f);  // Blanco
    gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(0.5f, 0.5f, -0.5f);
    gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(-0.5f, 0.5f, -0.5f);
    gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(-0.5f, -0.5f, -0.5f);
    gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(0.5f, -0.5f, -0.5f);

    gl.glEnd();
}

    void drawWings() {
        

        gl.glBegin(GL2.GL_QUADS);
        // Alas
        //Cara Superior
        gl.glColor3f(1.0f, 1.0f, 1.0f); // Color blanco
        gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(-0.55f, 0.5f, 0.6f);
        gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(0.55f, 0.5f, 0.6f);
        gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(0.55f, 0.5f, -0.4f);
        gl.glTexCoord2f(0f, 1.0f);gl.glVertex3f(-0.55f, 0.5f, -0.4f);
        
        // Cara inferior
        gl.glColor3f(1.0f , 1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(-0.55f, -0.4f, 0.6f);
        gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(0.55f, -0.4f, 0.6f);
        gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(0.55f, -0.4f,- 0.4f);
        gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(-0.55f, -0.4f,-0.4f);
        
        // Cara derecha
        gl.glColor3f(1.0f , 1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(0.55f, 0.5f, 0.6f);
        gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(0.55f, -0.4f, 0.6f);
        gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(0.55f, -0.4f, -0.4f);
        gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(0.55f, 0.5f, -0.4f);

        // Cara izquierda
        gl.glColor3f(1.0f , 1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(-0.55f, 0.5f, 0.6f);
        gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(-0.55f, -0.4f, 0.6f);
        gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(-0.55f, -0.4f, -0.4f);
        gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(-0.55f, 0.5f, -0.4f);

        // Cara frontal
        gl.glColor3f(1.0f , 1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(-0.55f, -0.4f, 0.6f);
        gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(0.55f, -0.4f, 0.6f);
        gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(0.55f, 0.5f, 0.6f);
        gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(-0.55f, 0.5f, 0.6f);

        // Cara trasera
        gl.glColor3f(1.0f , 1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(-0.55f, -0.4f, -0.40f);
        gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(0.55f, -0.4f, -0.40f);
        gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(0.55f, 0.5f, -0.40f);
        gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(-0.55f, 0.5f, -0.40f);
        

        gl.glEnd();
 
    }
    
    void drawHead(){
        //Cabeza
        gl.glBegin(GL2.GL_QUADS);

        // Cara Frontal 
        gl.glColor3f(1.0f , 1.0f, 1.0f);  //Blanco
        gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(-0.25f, 0.3f, -0.75f);  
        gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(0.25f, 0.3f, -0.75f);   
        gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(0.25f, 1.0f, -0.75f);
        gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(-0.25f, 1.0f, -0.75f); 

        // Cara Trasera 
        gl.glColor3f(1.0f , 1.0f, 1.0f);  //Blanco
        gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(-0.25f, 0.5f, -0.25f);  
        gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(0.25f, 0.5f, -0.25f); 
        gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(0.25f, 1.0f, -0.25f);   
        gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(-0.25f, 1.0f, -0.25f);  

        // Cara Superior
        gl.glColor3f(1.0f , 1.0f, 1.0f);  //Blanco
        gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(-0.25f, 1.0f, -0.25f);  
        gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(0.25f, 1.0f, -0.25f);   
        gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(0.25f, 1.0f, -0.75f);   
        gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(-0.25f, 1.0f, -0.75f);  

        // Cara Inferior
        gl.glColor3f(1.0f , 1.0f, 1.0f);  //Blanco
        gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(-0.25f, 0.5f, -0.5f);  
        gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(0.25f, 0.5f, -0.5f);   
        gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(0.25f, 0.5f, -0.75f);
        gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(-0.25f, 0.5f, -0.75f);
        
        // Cara Derecha
        gl.glColor3f(1.0f , 1.0f, 1.0f);  //Blanco
        gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(0.25f, 0.3f, -0.25f);  
        gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(0.25f, 1.0f, -0.25f);   
        gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(0.25f, 1.0f, -0.75f); 
        gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(0.25f, 0.3f, -0.75f);   

        // Cara Izquierda 
        gl.glColor3f(1.0f , 1.0f, 1.0f);  //Blanco
        gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(-0.25f, 0.3f, -0.25f);   
        gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(-0.25f, 1.0f, -0.25f);  
        gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(-0.25f, 1.0f, -0.75f);
        gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(-0.25f, 0.3f, -0.75f);
        
        //Cara Inferior p/spescuezo
        gl.glColor3f(1.0f , 1.0f, 1.0f);  //Blanco
        gl.glTexCoord2f(0.0f, 0.0f);gl.glVertex3f(-0.25f, 0.3f, -0.25f);
        gl.glTexCoord2f(1.0f, 0.0f);gl.glVertex3f(0.25f, 0.3f, -0.25f);
        gl.glTexCoord2f(1.0f, 1.0f);gl.glVertex3f(0.25f, 0.3f, -0.75f);
        gl.glTexCoord2f(0.0f, 1.0f);gl.glVertex3f(-0.25f, 0.3f, -0.75f);
        
        gl.glEnd();
    }
    
    void drawCrest() {
        gl.glBegin(GL2.GL_QUADS);
    
        // Abajo
        gl.glColor3f(1.0f, 0.0f, 0.0f);  // Rojo
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.15f, 0.3f, -0.75f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(0.15f, 0.3f, -0.75f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.15f, 0.3f, -0.8f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.15f, 0.3f, -0.8f);
        
        // Lado izquierdo
        gl.glColor3f(1.0f, 0.0f, 0.0f);  // Rojo
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.15f, 0.3f, -0.75f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-0.15f, 0.6f, -0.75f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-0.15f, 0.6f, -0.8f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.15f, 0.3f, -0.8f);
        
        // Lado derecho
        gl.glColor3f(1.0f, 0.0f, 0.0f);  // Rojo
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(0.15f, 0.3f, -0.75f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(0.15f, 0.6f, -0.75f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.15f, 0.6f, -0.8f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(0.15f, 0.3f, -0.8f);
        
        // Lado trasero
        gl.glColor3f(1.0f, 0.0f, 0.0f);  // Rojo
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.15f, 0.3f, -0.8f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(0.15f, 0.3f, -0.8f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.15f, 0.6f, -0.8f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.15f, 0.6f, -0.8f);
        
        gl.glEnd();
}    
    
    void drawBeak(){
    gl.glBegin(GL2.GL_QUADS);
    
    // Pico Abajo
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.15f, 0.6f, -0.9f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(0.15f, 0.6f, -0.9f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.15f, 0.6f, -0.7f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.15f, 0.6f, -0.7f);
    
    // Pico Arriba
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.15f, 0.75f, -0.9f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(0.15f, 0.75f, -0.9f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.15f, 0.75f, -0.7f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.15f, 0.75f, -0.7f);
    
    // Lado izquierdo
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.15f, 0.6f, -0.9f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-0.15f, 0.75f, -0.9f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-0.15f, 0.75f, -0.7f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.15f, 0.6f, -0.7f);
    
    // Lado derecho
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(0.15f, 0.6f, -0.9f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(0.15f, 0.75f, -0.9f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.15f, 0.75f, -0.7f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(0.15f, 0.6f, -0.7f);
    
    // Lado frontal
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.15f, 0.6f, -0.9f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(0.15f, 0.6f, -0.9f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.15f, 0.75f, -0.9f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.15f, 0.75f, -0.9f);
    
    gl.glEnd();
}

    void drawLegs(){
    gl.glBegin(GL2.GL_QUADS);
    
    // Pata izquierda
    
    // Cara delantera
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.35f, -1.15f, 0.0f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-0.2f, -1.15f, 0.0f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-0.2f, -0.5f, 0.0f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.35f, -0.5f, 0.0f);
    
    // Cara trasera
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.35f, -1.15f, 0.10f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-0.2f, -1.15f, 0.10f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-0.2f, -0.5f, 0.10f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.35f, -0.5f, 0.10f);
    
    // Cara derecha
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.2f, -1.15f, 0.0f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-0.2f, -1.15f, 0.10f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-0.2f, -0.5f, 0.10f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.2f, -0.5f, 0.0f);

    // Cara izquierda
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.35f, -1.15f, 0.0f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-0.35f, -1.15f, 0.10f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-0.35f, -0.5f, 0.10f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.35f, -0.5f, 0.0f);

    // Pata derecha
    
    // Cara frontal
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(0.2f, -1.15f, 0.0f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(0.35f, -1.15f, 0.0f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.35f, -0.5f, 0.0f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(0.2f, -0.5f, 0.0f);
    
    // Cara trasera
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(0.2f, -1.15f, 0.10f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(0.35f, -1.15f, 0.10f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.35f, -0.5f, 0.10f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(0.2f, -0.5f, 0.10f);
    
    // Cara derecha
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(0.2f, -1.15f, 0.0f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(0.2f, -1.15f, 0.10f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.2f, -0.5f, 0.10f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(0.2f, -0.5f, 0.0f);
    
    // Cara izquierda
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(0.35f, -1.15f, 0.0f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(0.35f, -1.15f, 0.10f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.35f, -0.5f, 0.10f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(0.35f, -0.5f, 0.0f);
    
    gl.glEnd();
}

    void drawClaws() {
    // Garras del pollo
    // Cara Pata derecha
    gl.glBegin(GL2.GL_QUADS);

    // Cara trasera
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(0.45f, -1.25f, 0.1f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(0.1f, -1.25f, 0.1f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.1f, -1.15f, 0.1f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(0.45f, -1.15f, 0.1f);

    // Cara frontal
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(0.1f, -1.25f, -0.25f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(0.45f, -1.25f, -0.25f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.45f, -1.15f, -0.25f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(0.1f, -1.15f, -0.25f);

    // Cara derecha
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(0.45f, -1.25f, 0.1f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(0.45f, -1.25f, -0.25f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.45f, -1.15f, -0.25f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(0.45f, -1.15f, 0.1f);

    // Cara izquierda
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(0.1f, -1.25f, 0.1f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(0.1f, -1.25f, -0.25f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.1f, -1.15f, -0.25f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(0.1f, -1.15f, 0.1f);

    // Cara inferior
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(0.45f, -1.25f, -0.25f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(0.1f, -1.25f, -0.25f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.1f, -1.25f, 0.1f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(0.45f, -1.25f, 0.1f);

    // Cara superior
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(0.45f, -1.15f, -0.25f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(0.1f, -1.15f, -0.25f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.1f, -1.15f, 0.1f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(0.45f, -1.15f, 0.1f);

    gl.glEnd();

    // Garras pata izquierda
    gl.glBegin(GL2.GL_QUADS);

    // Cara trasera
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.45f, -1.25f, 0.1f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-0.1f, -1.25f, 0.1f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-0.1f, -1.15f, 0.1f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.45f, -1.15f, 0.1f);

    // Cara frontal
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.1f, -1.25f, -0.25f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-0.45f, -1.25f, -0.25f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-0.45f, -1.15f, -0.25f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.1f, -1.15f, -0.25f);

    // Cara derecha
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.45f, -1.25f, 0.1f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-0.45f, -1.25f, -0.25f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-0.45f, -1.15f, -0.25f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.45f, -1.15f, 0.1f);

    // Cara izquierda
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.1f, -1.25f, 0.1f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-0.1f, -1.25f, -0.25f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-0.1f, -1.15f, -0.25f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.1f, -1.15f, 0.1f);

    // Cara inferior
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.45f, -1.25f, -0.25f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-0.1f, -1.25f, -0.25f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-0.1f, -1.25f, 0.1f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.45f, -1.25f, 0.1f);

    // Cara superior
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.45f, -1.15f, -0.25f);
    gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-0.1f, -1.15f, -0.25f);
    gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-0.1f, -1.15f, 0.1f);
    gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.45f, -1.15f, 0.1f);

    gl.glEnd();
}
    
    void drawEyes(){
        // Ojo izquierdo (cuadro negro)
        gl.glBegin(GL2.GL_QUADS);

        gl.glColor3f(1.0f, 1.0f, 1.0f);  // Blanco
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.25f, 0.75f, -0.76f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-0.15f, 0.75f, -0.76f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-0.15f, 0.85f, -0.76f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.25f, 0.85f, -0.76f);
    
        // Ojo derecho (cuadro negro)
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(0.25f, 0.75f, -0.76f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(0.15f, 0.75f, -0.76f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(0.15f, 0.85f, -0.76f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(0.25f, 0.85f, -0.76f);
        
        gl.glEnd();
    }


    
    @Override
    public void dispose(GLAutoDrawable drawable) {
        
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GLU glu = new GLU();

        if (height == 0) {
            height = 1;
        }

        float aspect = (float) width / height;

        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0, aspect, 0.3, 100.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
}
