package test.api;

import api.HarmonizeResource;
import com.sun.jersey.api.client.ClientResponse;
import com.yammer.dropwizard.testing.ResourceTest;
import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import pdfTrat.CmmiFullProcessDocument;
import pdfTrat.FullProcessDocument;
import pdfTrat.FullProcessDocumentImp;
import persistence.firstfilter.iso.model.Task;
import services.cmmi.CmmiService;
import services.iso.IsoService;
import utils.PathFiles;
import utils.UtilsFile;

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Cristian del Cerro.
 */

public class HarmonizeResourceTest extends ResourceTest implements PathFiles {


    private FullProcessDocumentImp fullProcessDocumentImpMock;
    private CmmiFullProcessDocument cmmiFullProcessDocumentMock;
    private IsoService isoServiceMock;
    private CmmiService cmmiServiceMock;

    @Override
    protected void setUpResources() throws Exception {
        fullProcessDocumentImpMock = mock(FullProcessDocumentImp.class);
        cmmiFullProcessDocumentMock = mock(CmmiFullProcessDocument.class);
        isoServiceMock = mock(IsoService.class);
        cmmiServiceMock = mock(CmmiService.class);

        HarmonizeResource harmonizeResource = new HarmonizeResource(isoServiceMock, fullProcessDocumentImpMock,
                cmmiServiceMock, cmmiFullProcessDocumentMock);

        addResource(harmonizeResource);
    }

    @Test
    public void testUploadFile() throws IOException {


        ClientResponse response = client().resource("/harmonize/api").queryParam("name", "nameTest").queryParam("type", "cmmi")
                .type(MediaType.MULTIPART_FORM_DATA_TYPE).post(ClientResponse.class, null );

        assertThat(response.getStatus()).isEqualTo(200);

        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        verify(cmmiServiceMock).createStandard(nameCaptor.capture());

        assertThat(nameCaptor.getValue()).isEqualTo("nameTest");


    }

    @Test
    public void testUploadFileWithoutName() throws IOException {

        ClientResponse response = client().resource("/harmonize/api").queryParam("type", "cmmi")
                .type(MediaType.MULTIPART_FORM_DATA_TYPE).post(ClientResponse.class, null );

        assertThat(response.getStatus()).isEqualTo(400);

    }

    @Test
    public void testUploadFileWithoutType() throws IOException {

        ClientResponse response = client().resource("/harmonize/api").queryParam("name", "nameTest")
                .type(MediaType.MULTIPART_FORM_DATA_TYPE).post(ClientResponse.class, null );

        assertThat(response.getStatus()).isEqualTo(400);

    }
}