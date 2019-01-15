/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package pathweaver.lib;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;

/**
 * Add your docs here.
 */
public class CsvToTrajectories 
{

    public static ArrayList<Trajectory> dirToTrajectories(String path)
    {
        ArrayList<Trajectory> trajs = new ArrayList<Trajectory>();
        Path dir = Paths.get(path);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) 
        {
            for (Path file : stream) 
            {
                String str = "";
                str = file.getFileName().toString().substring(0, file.getFileName().toString().indexOf(".pf1.csv"));
                trajs.add(PathfinderFRC.getTrajectory(str));
                System.out.println();
            }
        } 
        catch (IOException | DirectoryIteratorException x) 
        {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can only be thrown by newDirectoryStream.
            System.err.println(x);
        }

        return trajs;
    }
}
