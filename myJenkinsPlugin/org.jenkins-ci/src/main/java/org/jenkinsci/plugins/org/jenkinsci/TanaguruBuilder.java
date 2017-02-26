package org.jenkinsci.plugins.org.jenkinsci;

import hudson.Launcher;
import hudson.Extension;
import hudson.FilePath;
import hudson.util.FormValidation;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import jenkins.tasks.SimpleBuildStep;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.QueryParameter;

import javax.servlet.ServletException;
import java.io.IOException;
import org.tanaguru.jenkins.rest.AuditRestServiceImpl_JerseyClient;
import org.tanaguru.jenkins.rest.MyJerseyClient;

/**
 * Sample {@link Builder}.
 *
 * <p>
 * When the user configures the project and enables this builder,
 * {@link DescriptorImpl#newInstance(StaplerRequest)} is invoked and a new
 * {@link TanaguruBuilder} is created. The created instance is persisted to the
 * project configuration XML by using XStream, so this allows you to use
 * instance fields (like {@link #name}) to remember the configuration.
 *
 * <p>
 * When a build is performed, the {@link #perform} method will be invoked.
 *
 * @author mkebri
 */
public class TanaguruBuilder extends Builder implements SimpleBuildStep {

    private final String name;
    private final String urlTanaguruWebService;
    private final int performanceUnstableMark;
    private final int performanceFailedMark;

    // Fields in config.jelly must match the parameter names in the "DataBoundConstructor"
    @DataBoundConstructor
    public TanaguruBuilder(String name, String urlTanaguruWebService, int performanceUnstableMark, int performanceFailedMark) {
        this.name = name;
        this.urlTanaguruWebService = urlTanaguruWebService;
        this.performanceUnstableMark= performanceUnstableMark;
        this.performanceFailedMark= performanceFailedMark;
    }

    /**
     * We'll use this from the {@code config.jelly}.
     */
    public String getName() {
        return name;
    }

    /**
     * We'll use this from the {@code config.jelly}.
     */
    public String getUrlTanaguruWebService() {
        return urlTanaguruWebService;
    }

    public int getPerformanceUnstableMark() {
        return performanceUnstableMark;
    }

    public int getPerformanceFailedMark() {
        return performanceFailedMark;
    }
    
    
    
    
    
    

    @Override
    public void perform(Run<?, ?> build, FilePath workspace, Launcher launcher, TaskListener listener) {
        // This is where you 'build' the project.
        // Since this is a dummy, we just say 'hello world' and call that a build.

        // This also shows how you can consult the global configuration of the builder
        if (getDescriptor().getUseFrench()) {
            listener.getLogger().println("Bonjour, " + name + "!");
        } else {
            listener.getLogger().println("Hello, " + name + "!");
        }

        listener.getLogger().println("Running Tanaguru Rest API call .....");
        
        MyJerseyClient myJerseyClient = new MyJerseyClient(urlTanaguruWebService);

        listener.getLogger().println("Result of Tanaguru Rest API call :"+ myJerseyClient.getTestConnection());
        
        
        
        
    }

    // Overridden for better type safety.
    // If your plugin doesn't really define any property on Descriptor,
    // you don't have to do this.
    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl) super.getDescriptor();

    }

    /**
     * Descriptor for {@link TanaguruBuilder}. Used as a singleton. The class is
     * marked as public so that it can be accessed from views.
     *
     * <p>
     * See
     * {@code src/main/resources/hudson/plugins/hello_world/TanaguruBuilder/*.jelly}
     * for the actual HTML fragment for the configuration screen.
     */
    @Extension // This indicates to Jenkins that this is an implementation of an extension point.
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        /**
         * To persist global configuration information, simply store it in a
         * field and call save().
         *
         * <p>
         * If you don't want fields to be persisted, use {@code transient}.
         */
        private boolean useFrench;

        /**
         * In order to load the persisted global configuration, you have to call
         * load() in the constructor.
         */
        public DescriptorImpl() {
            load();
        }

        /**
         * Performs on-the-fly validation of the form field 'name'.
         *
         * @param value This parameter receives the value that the user has
         * typed.
         * @return Indicates the outcome of the validation. This is sent to the
         * browser.
         * <p>
         * Note that returning {@link FormValidation#error(String)} does not
         * prevent the form from being saved. It just means that a message will
         * be displayed to the user.
         */
        public FormValidation doCheckName(@QueryParameter String value)
                throws IOException, ServletException {
            if (value.length() == 0) {
                return FormValidation.error("Please set a name");
            }
            if (value.length() < 4) {
                return FormValidation.warning("Isn't the name too short?");
            }
            return FormValidation.ok();
        }

        public FormValidation doCheckUrlTanaguruWebService(@QueryParameter String value)
                throws IOException, ServletException {
            if (value.length() == 0) {
                return FormValidation.error("Please set an url to the tanaguru web service !" + "Has to start with 'http://' or 'https://'");
            }
            if (value.length() < 4) {
                return FormValidation.warning("Isn't the url too short or invalid ?");
            }
            return FormValidation.ok();
        }

        //validate forme
        public FormValidation doTestTanaguruRestConnection(@QueryParameter final String urlTanaguruWebService) {
            FormValidation validationResult;
            MyJerseyClient myJerseyClient = new MyJerseyClient(urlTanaguruWebService);

            if (myJerseyClient.getTestConnection().equalsIgnoreCase("it works")) {
                validationResult = FormValidation.ok("Connection Ok!");
            } else {
                validationResult = FormValidation.error("REST uri is not valid, cannot be empty and has to "
                        + "start with 'http://' or 'https://'");
            }

            return validationResult;
        }

        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            // Indicates that this builder can be used with all kinds of project types 
            return true;
        }

        /**
         * This human readable name is used in the configuration screen.
         */
        public String getDisplayName() {
            return "Jenkins Tanaguru Plugin";
        }

        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
            // To persist global configuration information,
            // set that to properties and call save().
            useFrench = formData.getBoolean("useFrench");
            // ^Can also use req.bindJSON(this, formData);
            //  (easier when there are many fields; need set* methods for this, like setUseFrench)
            save();
            return super.configure(req, formData);
        }

        /**
         * This method returns true if the global configuration says we should
         * speak French.
         *
         * The method name is bit awkward because global.jelly calls this method
         * to determine the initial state of the checkbox by the naming
         * convention.
         */
        public boolean getUseFrench() {
            return useFrench;
        }
    }
}
