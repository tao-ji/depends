package depends.extractor.python;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import depends.deptypes.DependencyType;
import depends.entity.FunctionEntity;

public class PythonDecoratorTest extends PythonParserTest {
    @Before
    public void setUp() {
    	super.init();
    }

	
	@Test
	public void could_parse_decorated() throws IOException {
		String[] srcs = new String[] {
	    		"./src/test/resources/python-code-examples/decorated.py",
	    	    };
	    
	    for (String src:srcs) {
		    PythonFileParser parser = createParser(src);
		    parser.parse();
	    }
	    inferer.resolveAllBindings();
	    FunctionEntity func = (FunctionEntity)repo.getEntity("foo");
		this.assertContainsRelation(func, DependencyType.ANNOTATION	, "our_decorator");
	    assertEquals(1,func.getResolvedAnnotations().size());
	}

}
