package org.kevoree.library.ec2;

import org.kevoree.annotation.DictionaryAttribute;
import org.kevoree.annotation.DictionaryType;
import org.kevoree.annotation.Library;
import org.kevoree.annotation.NodeType;
import org.kevoree.library.sky.api.nodeType.PJavaSENode;

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 04/06/13
 * Time: 16:23
 *
 * @author Erwan Daubert
 * @version 1.0
 */
@Library(name = "EC2")
@NodeType
@DictionaryType({
        @DictionaryAttribute(name = "imageName", defaultValue = "debian.img", optional = false)
})
public class PEc2Node extends PJavaSENode {
}
