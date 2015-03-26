package site.server.printer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import site.server.bean.tree.TreeNode;

public class TreePrinter {
	
	public void printTree(TreeNode root, String outputFile) throws IOException {
		final BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
		final List<TreeNode> nodes = new LinkedList<TreeNode>();
		final Map<TreeNode, TreeNode> edges = new HashMap<TreeNode, TreeNode>();
		nodes.add(root);
		if (root.getSons() != null) {
			for (final TreeNode node : root.getSons()) {
				nodes.add(node);
				edges.put(root, node);
				printTree(root, outputFile);
			}
		}
	}

}
