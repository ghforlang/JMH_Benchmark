package jmh.exmple2;

import jmh.dataobject.UserModel;
import jmh.dataobject.UserVO;
import jmh.exmple1.HelloWorld;
import net.sf.cglib.beans.BeanCopier;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.beans.BeanUtils;

/**
 * @author fanwh
 * @version v1.0
 * @decription
 * @create on 2017/12/5 13:25
 */
@State(Scope.Thread)
public class CopyPropertiesBenchmark {
    private UserModel model = new UserModel();
    private BeanCopier beanCopier = BeanCopier.create(UserModel.class, UserVO.class, false);

    /**
     * 人工setter复制属性
     *
     * @return
     */
    @Benchmark
    public UserVO manuallySetter() {
        UserVO vo = new UserVO();
        vo.setName(model.getName());
        vo.setNickName(model.getNickName());
        vo.setPassword(model.getPassword());
        return vo;
    }

    @Benchmark
    public UserVO beanUitls() {
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(this.model, vo);
        return vo;
    }

    @Benchmark
    public UserVO beanCopier() {
        UserVO vo = new UserVO();
        beanCopier.copy(this.model, vo, null);
        return vo;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(CopyPropertiesBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
