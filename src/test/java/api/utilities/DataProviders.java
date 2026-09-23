package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "AllData")
    public String[][] AllDataProvider() {

        String fName =
                System.getProperty("user.dir")
                + "//TestData//test case of API.xlsx";

        int ttlRowCnt =
                ReadExcelFiles.getRowCount(
                        fName,
                        "TestData"
                );

        int ttlColCnt =
                ReadExcelFiles.getColCount(
                        fName,
                        "TestData"
                );

        String[][] tempData =
                new String[ttlRowCnt - 1][ttlColCnt];

        int dataRow = 0;

        for (int rowNo = 1; rowNo < ttlRowCnt; rowNo++) {

            String userId =
                    ReadExcelFiles.getCellValue(
                            fName,
                            "TestData",
                            rowNo,
                            0
                    );

            // Skip blank rows
            if (userId == null || userId.trim().isEmpty()) {
                continue;
            }

            for (int colNo = 0; colNo < ttlColCnt; colNo++) {

                tempData[dataRow][colNo] =
                        ReadExcelFiles.getCellValue(
                                fName,
                                "TestData",
                                rowNo,
                                colNo
                        );
            }

            dataRow++;
        }

        String[][] userData =
                new String[dataRow][ttlColCnt];

        for (int i = 0; i < dataRow; i++) {
            userData[i] = tempData[i];
        }

        return userData;
    }


    @DataProvider(name = "UserNamesData")
    public String[][] UserNamesDataProvider() {

        String fName =
                System.getProperty("user.dir")
                + "//TestData//test case of API.xlsx";

        int ttlRowCnt =
                ReadExcelFiles.getRowCount(
                        fName,
                        "TestData"
                );

        String[][] userNames =
                new String[ttlRowCnt - 1][1];

        int dataRow = 0;

        for (int rowNo = 1; rowNo < ttlRowCnt; rowNo++) {

            String username =
                    ReadExcelFiles.getCellValue(
                            fName,
                            "TestData",
                            rowNo,
                            1
                    );

            // Skip blank rows
            if (username == null || username.trim().isEmpty()) {
                continue;
            }

            userNames[dataRow][0] = username;

            dataRow++;
        }

        String[][] finalData =
                new String[dataRow][1];

        for (int i = 0; i < dataRow; i++) {
            finalData[i] = userNames[i];
        }

        return finalData;
    }
}