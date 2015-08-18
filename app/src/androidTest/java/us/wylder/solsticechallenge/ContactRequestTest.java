package us.wylder.solsticechallenge;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

import us.wylder.solsticechallenge.data.Contact;

/**
 * Created by Matt on 8/17/2015.
 */
public class ContactRequestTest extends TestCase {

    private String jsonSource = "    {\n" +
            "        \"name\": \"Essie Vaill\",\n" +
            "        \"employeeId\":1,\n" +
            "        \"company\": \"Litronic Industries\",\n" +
            "        \"detailsURL\":\"https://solstice.applauncher.com/external/Contacts/id/1.json\",\n" +
            "        \"smallImageURL\": \"https://solstice.applauncher.com/external/Contacts/images/image1_small.jpeg\",\n" +
            "        \"birthdate\": \"1382659557\",\n" +
            "        \"phone\": {\n" +
            "            \"work\": \"602-252-4827\",\n" +
            "            \"home\": \"602-252-4009\",\n" +
            "            \"mobile\": \"650-252-4010\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": \"Cruz Roudabush\",\n" +
            "        \"employeeId\":2,\n" +
            "        \"company\": \"Meridian Products\",\n" +
            "        \"detailsURL\":\"https://solstice.applauncher.com/external/Contacts/id/2.json\",\n" +
            "        \"smallImageURL\": \"https://solstice.applauncher.com/external/Contacts/images/image2_small.jpeg\",\n" +
            "        \"birthdate\": \"558289857\",\n" +
            "        \"phone\": {\n" +
            "            \"work\": \"907-345-0962\",\n" +
            "            \"home\": \"907-345-1215\",\n" +
            "            \"mobile\": \"650-345-1220\"\n" +
            "        }\n" +
            "    }]";

    public ContactRequestTest(){
        super();
        try {
            convertTest();
        } catch (IOException e){
            fail();
        }
    }

    public void convertTest() throws JsonParseException, JsonMappingException, IOException{

        ObjectMapper mapper = new ObjectMapper();
        List<Contact> contacts = mapper.readValue(
                jsonSource,
                mapper.getTypeFactory().constructCollectionType(
                        List.class, Contact.class));

        assertEquals(2, contacts.size());
    }

}
