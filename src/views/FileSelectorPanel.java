package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class FileSelectorPanel extends BasePanel implements TreeSelectionListener {
	private JTree tree;
	
	public FileSelectorPanel(MainFrame main) {
		this(main, null);
	}
  
	public FileSelectorPanel(MainFrame main, File dir) {
		super(main);
		setLayout(new BorderLayout());
		
		openFileOrDirectory(dir);
	}
  
	public void openFileOrDirectory(File dir) {
		removeAll();
		if (dir != null) {
			tree = new JTree(populateTree(new DefaultMutableTreeNode(dir), dir));
		} else {
			tree = new JTree(new DefaultMutableTreeNode("Open a directory or file through the menu!"));
		}
		tree.addTreeSelectionListener(this);
	    JScrollPane scrollpane = new JScrollPane(tree);
	    
	    add(BorderLayout.CENTER, scrollpane);
	    validate();
	    repaint();
	}
  
	private DefaultMutableTreeNode populateTree(DefaultMutableTreeNode curNode, File dir) {
		if (dir != null && dir.isDirectory()) {
			String[] paths = dir.list();
			Arrays.sort(paths, String.CASE_INSENSITIVE_ORDER);
		  
			//have to add directories then files, or else ordering is wrong
			List<DefaultMutableTreeNode> directoryList = new ArrayList<DefaultMutableTreeNode>();
			List<DefaultMutableTreeNode> fileList = new ArrayList<DefaultMutableTreeNode>();
			for (String fileName : paths) {
				File nextFile = new File(dir.getAbsolutePath() + File.separator + fileName);
				if (nextFile.isDirectory()) {
					NodeItem nodeItem = new NodeItem(nextFile);
					directoryList.add(populateTree(new DefaultMutableTreeNode(nodeItem), nextFile));
				} else {
					if (fileName.endsWith(".sm")) {
						NodeItem nodeItem = new NodeItem(nextFile);
						fileList.add(populateTree(new DefaultMutableTreeNode(nodeItem), nextFile));
					}
				}
			}
		  
			addAll(curNode, directoryList);
			addAll(curNode, fileList);
		} 
		return curNode;
	}
  
	private DefaultMutableTreeNode addAll(DefaultMutableTreeNode node, List<DefaultMutableTreeNode> toAddNodes) {
		for (DefaultMutableTreeNode toAddNode : toAddNodes) {
			node.add(toAddNode);
		}
		return node;
	}

	public Dimension getMinimumSize() {
		return new Dimension(200, 400);
	}

	public Dimension getPreferredSize() {
		return new Dimension(200, 400);
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)e.getPath().getLastPathComponent();
		if (node.getUserObject() instanceof NodeItem) {
			File selectedFile = ((NodeItem)node.getUserObject()).file;
			if (selectedFile.isFile()) {
				main.openStepFile(selectedFile.getAbsolutePath());
			}
		}
	}
	
	private class NodeItem {
		public File file;
		
		public NodeItem(File file) {
			this.file = file;
		}
		
		@Override
		public String toString() {
			return file.getName();
		}
	}
}