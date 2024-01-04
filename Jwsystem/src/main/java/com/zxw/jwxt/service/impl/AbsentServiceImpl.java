package com.zxw.jwxt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxw.jwxt.domain.Absent;
import com.zxw.jwxt.domain.TUser;
import com.zxw.jwxt.domain.UserRealm;
import com.zxw.jwxt.mapper.AbsentMapper;
import com.zxw.jwxt.service.IAbsentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zxw
 * @since 2020-01-29
 */
@Service
public class AbsentServiceImpl extends ServiceImpl<AbsentMapper, Absent> implements IAbsentService {

    @Autowired
    private AbsentMapper absentMapper;

    @Override
    public int[][] countStudentAbsent(String userId) {
        List<Absent> list = absentMapper.countStudentAbsent(userId, getFirstWeekDay(new Date()), new Date());
        int[][] arr = countAbsent(list);
        return arr;
    }

    @Override
    public int[][] countStudentByJW(UserRealm realm) {
        TUser user = (TUser) realm;
        List<Absent> list = absentMapper.countStudentByJW(user.getCollegeId(), getFirstWeekDay(new Date()), new Date());
        int[][] arr = countAbsent(list);
        return arr;
    }

    public Date getFirstWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, date.getDate() - 6);
        return calendar.getTime();
    }

    public int[][] countAbsent(List<Absent> list) {
        int[][] arr = new int[7][5];
        for (Absent absent : list) {
            if (absent.getSectionId().equals("1") || absent.getSectionId().equals("2") || absent.getSectionId().equals("3") || absent.getSectionId().equals("4") || absent.getSectionId().equals("5")) {
                if (absent.getSectionId().equals("1")) {
                    arr[0][0]++;
                } else if (absent.getSectionId().equals("2")) {
                    arr[0][1]++;
                } else if (absent.getSectionId().equals("3")) {
                    arr[0][2]++;
                } else if (absent.getSectionId().equals("4")) {
                    arr[0][3]++;
                } else if (absent.getSectionId().equals("5")) {
                    arr[0][4]++;
                }
            }
            if (absent.getSectionId().equals("6") || absent.getSectionId().equals("7") || absent.getSectionId().equals("8") || absent.getSectionId().equals("9") || absent.getSectionId().equals("10")) {
                if (absent.getSectionId().equals("6")) {
                    arr[1][0]++;
                } else if (absent.getSectionId().equals("7")) {
                    arr[1][1]++;
                } else if (absent.getSectionId().equals("8")) {
                    arr[1][2]++;
                } else if (absent.getSectionId().equals("9")) {
                    arr[1][3]++;
                } else if (absent.getSectionId().equals("10")) {
                    arr[1][4]++;
                }
            }
            if (absent.getSectionId().equals("11") || absent.getSectionId().equals("12") || absent.getSectionId().equals("13") || absent.getSectionId().equals("14") || absent.getSectionId().equals("15")) {
                if (absent.getSectionId().equals("11")) {
                    arr[2][0]++;
                } else if (absent.getSectionId().equals("12")) {
                    arr[2][1]++;
                } else if (absent.getSectionId().equals("13")) {
                    arr[2][2]++;
                } else if (absent.getSectionId().equals("14")) {
                    arr[2][3]++;
                } else if (absent.getSectionId().equals("15")) {
                    arr[2][4]++;
                }
            }
            if (absent.getSectionId().equals("16") || absent.getSectionId().equals("17") || absent.getSectionId().equals("18") || absent.getSectionId().equals("19") || absent.getSectionId().equals("20")) {
                if (absent.getSectionId().equals("16")) {
                    arr[3][0]++;
                } else if (absent.getSectionId().equals("17")) {
                    arr[3][1]++;
                } else if (absent.getSectionId().equals("18")) {
                    arr[3][2]++;
                } else if (absent.getSectionId().equals("19")) {
                    arr[3][3]++;
                } else if (absent.getSectionId().equals("20")) {
                    arr[3][4]++;
                }
            }
            if (absent.getSectionId().equals("21") || absent.getSectionId().equals("22") || absent.getSectionId().equals("23") || absent.getSectionId().equals("24") || absent.getSectionId().equals("25")) {
                if (absent.getSectionId().equals("21")) {
                    arr[4][0]++;
                } else if (absent.getSectionId().equals("22")) {
                    arr[4][1]++;
                } else if (absent.getSectionId().equals("23")) {
                    arr[4][2]++;
                } else if (absent.getSectionId().equals("24")) {
                    arr[4][3]++;
                } else if (absent.getSectionId().equals("25")) {
                    arr[4][4]++;
                }
            }
            if (absent.getSectionId().equals("26") || absent.getSectionId().equals("27") || absent.getSectionId().equals("28") || absent.getSectionId().equals("29") || absent.getSectionId().equals("30")) {
                if (absent.getSectionId().equals("26")) {
                    arr[5][0]++;
                } else if (absent.getSectionId().equals("27")) {
                    arr[5][1]++;
                } else if (absent.getSectionId().equals("28")) {
                    arr[5][2]++;
                } else if (absent.getSectionId().equals("29")) {
                    arr[5][3]++;
                } else if (absent.getSectionId().equals("30")) {
                    arr[5][4]++;
                }
            }
            if (absent.getSectionId().equals("31") || absent.getSectionId().equals("32") || absent.getSectionId().equals("33") || absent.getSectionId().equals("34") || absent.getSectionId().equals("35")) {
                if (absent.getSectionId().equals("31")) {
                    arr[6][0]++;
                } else if (absent.getSectionId().equals("32")) {
                    arr[6][1]++;
                } else if (absent.getSectionId().equals("33")) {
                    arr[6][2]++;
                } else if (absent.getSectionId().equals("34")) {
                    arr[6][3]++;
                } else if (absent.getSectionId().equals("35")) {
                    arr[6][4]++;
                }
            }
        }
        return arr;
    }

}
