package by.bsu.algorithms.labs.lab5.tree;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class AbstractGraphUtils<K> {
    public void print(String fileName) throws IOException {
        JGraphXAdapter<K, DefaultEdge> graphAdapter =
                new JGraphXAdapter<>(getGraph());
        mxIGraphLayout layout = new mxHierarchicalLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());
        BufferedImage image =
                mxCellRenderer.createBufferedImage(graphAdapter, null, 1, Color.WHITE, true, null);
        File imgFile = new File(fileName);
        ImageIO.write(image, "PNG", imgFile);
    }
    protected abstract Graph getGraph();
}
