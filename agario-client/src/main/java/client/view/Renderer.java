
package client.view;

import client.view.renderer.CircleRenderer;
import client.Util;
import client.model.map.Map;
import client.model.map.TestMap;
import client.model.map.object.Cell;
import client.model.map.object.MapObject;
import client.view.gl.GlException;
import client.view.gl.object.Program;
import client.view.gl.object.Shader;
import client.view.renderer.GridRenderer;
import client.view.settings.CameraSettings;

import java.io.IOException;
import java.util.List;

import org.joml.Matrix4f;
import org.joml.Vector2i;

import org.lwjgl.opengl.*; 
import static org.lwjgl.opengl.GL11.*;

/**
 * This class renders the map and the objects.
 * @author yzsolt
 */
public class Renderer {
    
    private final Window m_window;
    private Camera m_camera;
    private Program m_program;
    private Matrix4f m_world;
    
    public Renderer(Window window) {

        m_window = window;
        
        CameraSettings camera_settings = new CameraSettings();
        m_camera = new Camera(camera_settings, window.getSize());
        
        m_world = new Matrix4f().identity();
        
    }
    
    public void initialize() throws GlException, IOException {
        
        m_window.makeContextCurrent();
        
        GL.createCapabilities();
        
        // Load and compile shaders

        Shader vertex_shader = new Shader(Shader.Type.VERTEX, Util.getFileContents("dist/shaders/vertex_shader.glsl"));
        vertex_shader.compile();
     
        Shader fragment_shader = new Shader(Shader.Type.FRAGMENT, Util.getFileContents("dist/shaders/fragment_shader.glsl"));
        fragment_shader.compile();
        
        m_program = new Program();
        
        m_program.attachShader(vertex_shader);
        m_program.attachShader(fragment_shader);
        
        m_program.link();
        
        //m_window.setCursorLocked(true);
      
    }
    
    public void render(Map map, Cell player) throws GlException {
        
        glClearColor(0.7f, 0.7f, 0.7f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        
        glEnable(GL_DEPTH_TEST);
        glDisable(GL_CULL_FACE);
        
        m_program.use();
        
        // Update camera
        
        m_camera.setPosition(player.getPosition());
        // TODO: find a reasonable method to calculate height from the player's radius
        //m_camera.setHeight(player.getAttributes().getRadius() * 8);
        m_camera.setHeight(99);
        
        m_camera.setUniforms(m_program);
        
        // Draw background grid
        
        GridRenderer grid_renderer = GridRenderer.getInstance();
        grid_renderer.prepareRendering();
        
        grid_renderer.render(m_program, new Vector2i(map.getSize(), map.getSize()), 100, 100, 1.f);
        
        // Draw map objects
        
        List<MapObject> map_objects = map.getObjects();
        TestMap test_map = (TestMap)map;
        
        CircleRenderer circle_renderer = CircleRenderer.getInstance();
        circle_renderer.prepareRendering();
        
        for (MapObject map_object : map_objects) {
            circle_renderer.render(map_object, m_program);
        }
        
        /*
        int error;
        while ((error = glGetError()) != GL_NO_ERROR) {
            System.out.println("OpenGL error code: " + error);
        }
        */
        
        m_window.swapBuffers();
        
    }
    
}
