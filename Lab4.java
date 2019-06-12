public class Lab4
{
    public static void turnRight()
    {
        Robot.turnLeft();
        Robot.turnLeft();
        Robot.turnLeft();
    }

    public static void turnAround()
    {
        Robot.turnLeft();
        Robot.turnLeft();
    }

    public static void backUp()
    {
        turnAround();
        Robot.move();
        turnAround();
    }

    public static void completeBars()
    {
        while(Robot.frontIsClear()){
            Robot.turnLeft();
            completeOneBar();
            Robot.turnLeft();
            Robot.move(); 
        }
        Robot.turnLeft();
        completeOneBar();
    }

    public static void completeOneBar(){
        /* precondition: Robot is facing north on the bottom row, in a column
         * where the robot hasn't completed the bar.
         * postcondition: Robot is facing south in the same space and the robot
         * has completed the bar in the column
         */
        var count = 0;
        while(Robot.onDark()==false){
            Robot.makeDark();
            Robot.move();
            count+=1;
        }
        turnAround();
        while(count != 0){
            Robot.move();
            count-=1;
        }
    }

    public static void testCompleteBars1()
    {
        Robot.load("bars1.txt");
        Robot.setDelay(0.025);
        completeBars();
    }

    public static void testCompleteBars2()
    {
        Robot.load("bars2.txt");
        Robot.setDelay(0.025);
        completeBars();
    }

    public static void combinePiles()
    {
        var movecount = 0;
        var darkcount = 0;
        while(Robot.onDark()&&Robot.frontIsClear()){
            Robot.move();
            movecount+=1;
            darkcount+=1;
        }
        if(Robot.onDark()){
            darkcount+=1;
            Robot.makeLight();
        }
        turnAround();
        while(movecount != 0){
            Robot.move();
            Robot.makeLight();
            movecount-=1;
        }
        Robot.turnLeft();
        Robot.move();
        Robot.turnLeft();
        while(Robot.onDark()&&Robot.frontIsClear()){
            Robot.move();
        }
        turnAround();
        if(Robot.frontIsClear()){
            Robot.move();
        }else{
            Robot.makeDark();
            darkcount-=1;
        }
        turnAround();
        while(darkcount != 0){
            Robot.move();
            Robot.makeDark();
            darkcount-=1;
        }
    }

    public static void testCombinePiles1()
    {
        Robot.load("piles1.txt");
        Robot.setDelay(0.025);
        combinePiles();
    }

    public static void testCombinePiles2()
    {
        Robot.load("piles2.txt");
        Robot.setDelay(0.025);
        combinePiles();
    }

    public static void connectDots()
    {
        var count = 3;
        var stop = false;
        var endDraw = false;
        while(endDraw == false){
            while(count != 0 && stop == false){
                if(count == 3){
                    Robot.turnLeft();
                }else{
                    turnRight();
                }
                moveTwice();
                if(Robot.onDark()==false){
                    turnAround();
                    moveTwice();
                    turnAround();
                    count-=1;
                }else{
                    backUp();
                    Robot.makeDark();
                    Robot.move();
                    stop = true;
                }
            }
            if(count == 0){
                endDraw = true;
            }
            count = 3;
            stop = false;
        }

    }

    public static void moveTwice(){
        //robot moves 2 units forward
        Robot.move();
        Robot.move();
    }

    public static void testConnectDots1()
    {
        Robot.load("connect1.txt");
        Robot.setDelay(0.025);
        connectDots();
    }

    public static void testConnectDots2()
    {
        Robot.load("connect2.txt");
        Robot.setDelay(0.025);
        connectDots();
    }
}
