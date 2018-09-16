
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.bytedeco.javacv.*;

import static org.bytedeco.javacpp.opencv_core.IplImage;
import static org.bytedeco.javacpp.opencv_core.cvFlip;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;

/**
 * Created by gtiwari on 1/3/2017.
 */
public class Test extends JPanel implements Runnable {

    final int INTERVAL = 3000;///you may use interval
    CanvasFrame canvas = new CanvasFrame("Web Cam");
    FrameGrabber grabber = new VideoInputFrameGrabber(0); // 1 for next camera
    OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
    IplImage img;
    MyPojo pojo;

    public static final String ACCOUNT_SID = "AC76568a4d43d590ecef217f22ab299aeb";
    public static final String AUTH_TOKEN = "530479b901c025b39f9eb7213320188d";

    public void sendText(String name) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber("+12263873842"),
                new PhoneNumber("+12268871140"),
                "Hey! " + name + " is trying to get in.").create();
    }

    public void getImage() throws FileNotFoundException, IOException {
        IamOptions options = new IamOptions.Builder().apiKey("F4TaaiWSOyiHTRZ_cYo6kSE7e4WdTyJxEGEUg3xu7Lcg").build();

        VisualRecognition service = new VisualRecognition("2018-03-19", options);

        InputStream imagesStream = new FileInputStream("-aa.jpg");
        ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                .imagesFile(imagesStream)
                .imagesFilename("-aa.jpg")
                .threshold((float) 0.6)
                .owners(Arrays.asList("me"))
                .build();
        ClassifiedImages result = service.classify(classifyOptions).execute();

        ObjectMapper mapper = new ObjectMapper();
        pojo = mapper.readValue(result.toString(), MyPojo.class);
        
        System.out.println(result);
    }

    public Test() {
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        this.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent me) {
                cvSaveImage("-aa.jpg", img);
                try {
                    getImage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
               //MAYBE HARD CODE FOR STRANGER CRASH 
                if((Double.parseDouble(pojo.getImages()[0].getClassifiers()[0].getClasses()[0].getScore())) > 0.7)
                    sendText(pojo.getImages()[0].getClassifiers()[0].getClasses()[0].getClassif());
                else
                    sendText("A stranger");

            }

            public void mousePressed(MouseEvent me) {

            }

            public void mouseReleased(MouseEvent me) {

            }

            public void mouseEntered(MouseEvent me) {

            }

            public void mouseExited(MouseEvent me) {
            }

        });

    }

    public void run() {

        int i = 0;
        try {
            grabber.start();
            while (true) {
                Frame frame = grabber.grab();

                img = converter.convert(frame);

                //the grabbed frame will be flipped, re-flip to make it right
                cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise

                //save
                canvas.showImage(converter.convert(img));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Hello");
        Test gs = new Test();
        window.setContentPane(gs);
        window.setSize(100, 100);
        window.setLocation(750, 250);
        window.setVisible(true);
        Thread th = new Thread(gs);
        th.start();
    }
}
