//package com.icedragongame;
//
//import java.io.*;
//
///**
// * @author gengxuelong
// * @date 2023/6/30 17:25
// */
//public class AutoAnnotation {
//    File file = new File("F:\\backend\\src\\main\\java\\com\\icedragongame");
//    private static final String annotationForClass =
//            "/**\n" +
//                    " * <p>\n" +
//                    " *     project: snow_dragonGame blogSystem\n" +
//                    " *\n" +
//                    " *  该类名称为:\n" +
//                    " *     <name>\n" +
//                    " *\n" +
//                    " *  该类作用为:\n" +
//                    " *   <effect>\n" +
//                    " *\n" +
//                    " *@author gengxuelong\n" +
//                    " *\n" +
//                    " */";
//    private  static  final String annotationForValue =
//            "   /**\n" +
//                    "     * <p>\n" +
//                    "     *     project: snow_dragonGame blogSystem\n" +
//                    "     *\n" +
//                    "     *  该参数名称为:\n" +
//                    "     *     <name>\n" +
//                    "     *\n" +
//                    "     *  该参数描述为:\n" +
//                    "     *   <effect>\n" +
//                    "     *\n" +
//                    "     */";
//    private static final String annotaitonForMethod =
//            " /**\n" +
//                    "     * <p>\n" +
//                    "     *     project: snow_dragonGame blogSystem\n" +
//                    "     *\n" +
//                    "     *  该方法名称为:\n" +
//                    "     *     <name>\n" +
//                    "     *\n" +
//                    "     *  该方法作用为:\n" +
//                    "     *   <effect>\n" +
//                    "     *\n" +
//                    "     *   该方法设计参数描述:\n" +
//                    "     *   <description>\n" +
//                    "     *\n" +
//                    "     */";
//    public static void doAutoAnnotation(File file) throws IOException {
//        assert file.isDirectory();
//        File[] files = file.listFiles();
//        assert files != null;
//        for (File file1 : files) {
//            if(file1.isFile()){
//                String[] split = file1.getName().split("\\.");
//                String type = split[split.length-1];
//                if("java".equals(type)){
//                    handleJavaFile(file1);
//                }
//            }else{
//                doAutoAnnotation(file1);
//            }
//        }
//    }
//
//    private static void handleJavaFile(File file) throws IOException {
//        FileReader fileReader = new FileReader(file);
//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
//        String line = null;
//        String lineLast = null;
//        String lineLastLast = null;
//        while((line = bufferedReader.readLine())!=null){
//            if(wileDoWriteClass(line,lineLast,lineLastLast)){
//                bufferedWriter.write(annotationForClass);
//            }else if(wileDoWriteM(line,lineLast,lineLastLast)){
//                bufferedWriter.write(annotaitonForMethod);
//            }else if(wileDoWriteV(line,lineLast,lineLastLast)){
//                bufferedWriter.write(annotationForValue);
//            }
//            lineLastLast = lineLast;
//            lineLast = line;
//        }
//    }
//
//    private static boolean wileDoWriteV(String line, String lineLast, String lineLastLast) {
//    }
//
//
//    private static boolean wileDoWriteM(String line, String lineLast, String lineLastLast) {
//
//    }
//
//    private static boolean wileDoWriteClass(String line, String lineLast, String lineLastLast) {
//        if(line.startsWith(""))
//    }
//
//    public static void main(String[] args) {
//        File file = new File("F:\\backend\\src\\main\\java\\com\\icedragongame");
//        doAutoAnnotation(file);
//    }
//}
